package apis;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *  class for getting a comments from twitter app.
 */
public class TwitterAPI {

    private twitter4j.Twitter twitter;
    SenticNet senticNet;
    private double averageReviewPoint;


    /**
     * Connecting to a twitter app. You have to use your Consumer Keys and Access Tokens !!!!
     */
    public TwitterAPI() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("*************")
                .setOAuthConsumerSecret("********************")
                .setOAuthAccessToken("*************")
                .setOAuthAccessTokenSecret("****************");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        senticNet = SenticNet.getInstance();
    }

    /**
     * method that calculates review point according to keyword.
     * It also adds found tweets to Redis.
     *
     * @param searchKey key that used for searching
     * @return calculated reviewPoint of given keyword. (keywords are like "iPhoneX", "Galaxy S3" ...)
     */
    public double getReviewPoint(String searchKey) throws TwitterException {
        String keyword = searchKey.replaceAll("\\W", "");
        List<Tweet> tweetList = new ArrayList<>();

        try {
            Query query = new Query("(#" + keyword + ") AND ((good) OR (bad) OR (like) OR (dislike) OR (hate) OR (love) OR (best)) exclude:retweets exclude:links exclude:replies"); //filter the query to get more qualified tweets
            query.lang("en");//languages of tweets is english
            query.count(10);//max 10 tweets
            QueryResult result = twitter.search(query); //send query
            List<Status> tweets = result.getTweets();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            double totalReviewPoint = 0.00;
            for (Status tweet : tweets) {
                double tweetReviewPoint = senticNet.calculateReviewPoint(tweet.getText()); // calculate the review point of each tweet.
                totalReviewPoint += tweetReviewPoint;
                Tweet newTweet = new Tweet(tweet.getId(), tweet.getText(), tweet.getUser().getScreenName(), df.format(tweet.getCreatedAt()), tweetReviewPoint);
                tweetList.add(newTweet);
            }
            tweetList.sort(Tweet::compareTo); //sort the tweets before inserting them to redis
            Redis db =  Redis.getInstance();
            db.addNewTweets(keyword, tweetList);

            if (totalReviewPoint == 0 ) // if we do not get tweets from twitter,review point of product will be zero
                averageReviewPoint=0;
            else
                averageReviewPoint = totalReviewPoint / tweetList.size();



        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        return averageReviewPoint;
    }
}