package apis;

import strategyPattern.Calculation;
import strategyPattern.CalculationStrategy1;
import strategyPattern.CalculationStrategy2;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * class for getting a comments from twitter app.
 */
public class TwitterAPI {

    private twitter4j.Twitter twitter;
    private Calculation calculationStrategy1;
    private Calculation calculationStrategy2;


    /**
     * Connecting to a twitter app. You have to use your Consumer Keys and Access Tokens !!!!
     */
    public TwitterAPI() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        calculationStrategy1 = new CalculationStrategy1();
        calculationStrategy2 = new CalculationStrategy2();
    }

    /**
     * method that calculates review point according to keyword.
     * It also adds found tweets to Redis.
     *
     * @param searchKey key that used for searching
     * @return calculated reviewPoint of given keyword. (keywords are like "iPhoneX", "Galaxy S3" ...)
     */
    public List<Double> getReviewPoint(String searchKey) throws TwitterException {
        String keyword = searchKey.replaceAll("\\W", "");
        List<Tweet> tweetList = new ArrayList<>();
        List<Double> calList = new ArrayList<Double>();

        try {
            Query query = new Query("(#" + keyword + ") AND ((good) OR (bad) OR (like) OR (dislike) OR (hate) OR (love) OR (best)) exclude:retweets exclude:links exclude:replies"); //filter the query to get more qualified tweets
            query.lang("en");//languages of tweets is english
            query.count(10);//max 10 tweets
            QueryResult result = twitter.search(query); //send query
            List<Status> tweets = result.getTweets();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            double totalCalStrategy1 = 0.00;
            double totalCalStrategy2 = 0.00;
            for (Status tweet : tweets) {
                double calStrategy1 = calculationStrategy1.calculate(tweet); // calculate the review point of each tweet.
                totalCalStrategy1 += calStrategy1;
                double calStrategy2 = calculationStrategy2.calculate(tweet); // calculate the review point of each tweet.
                totalCalStrategy2 += calStrategy2;
                Tweet newTweet = new Tweet(tweet.getId(), tweet.getText(), tweet.getUser().getScreenName(), df.format(tweet.getCreatedAt()), calStrategy1, calStrategy2);
                tweetList.add(newTweet);
            }
            //tweetList.sort(Tweet::compareTo); //sort the tweets before inserting them to redis // We have to sort when we pull the tweets from redis to show user depends on their selection
            Redis db = Redis.getInstance();
            db.addNewTweets(keyword, tweetList);

            double averageReviewPoint2;
            double averageReviewPoint1;

            if (totalCalStrategy1 == 0) { // if we do not get tweets from twitter,review point of product will be zero
                averageReviewPoint1 = 0;
                averageReviewPoint2 = 0;
            } else {
                averageReviewPoint1 = totalCalStrategy1 / tweetList.size();
                averageReviewPoint2 = totalCalStrategy2 / tweetList.size();
                calList.add(averageReviewPoint1);
                calList.add(averageReviewPoint2);
            }

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        return calList;
    }

}