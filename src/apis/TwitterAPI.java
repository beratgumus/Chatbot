package apis;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Static class for getting a comments from twitter app.
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
        cb.setDebugEnabled(true).setOAuthConsumerKey("***********")
                .setOAuthConsumerSecret("*********")
                .setOAuthAccessToken("**********")
                .setOAuthAccessTokenSecret("**********");



        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        senticNet = new SenticNet();
    }

    /**
     * method that returns the list of Tweet according to keyword.
     *
     * @param searchKey key that used for searching
     * @return set of a Tweet objects as a list.
     */
    public double getReviewPoint(String searchKey) throws TwitterException {
        String keyword = searchKey.replaceAll("\\W", "");
        List<Tweet> tweetList = new ArrayList<Tweet>();

        try {
            //ToDo: need to improvment on query
            Query query = new Query("(#" + keyword + ") AND ((good) OR (bad)) exclude:retweets exclude:links");
            query.count(10);
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            double reviewPoint = 0.00;
            for (Status tweet : tweets) {
                double tweetReviewPoint = 0.00;
                System.out.println("@" + tweet.getUser().getScreenName() + " [id]: " + tweet.getId() + " : " + tweet.getText() + "[date]: " + tweet.getCreatedAt() + "\n");
                tweetReviewPoint = senticNet.calculateReviewPoint(tweet.getText());
                reviewPoint += tweetReviewPoint;
                Tweet newTweet = new Tweet(tweet.getId(), tweet.getText(), tweet.getUser().getScreenName(), df.format(tweet.getCreatedAt()), tweetReviewPoint);
                tweetList.add(newTweet);
                System.out.println("Tweet review point: " + tweetReviewPoint);
            }
            RedisNew db = new RedisNew();
            db.addNewTweet(keyword, tweetList);
            averageReviewPoint = reviewPoint / tweetList.size();

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }

        return averageReviewPoint;
    }


}