import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Static class for getting a comments from twitter app.
 */
public class TwitterAPI {
    /**
     * Static method that returns the list of Tweet according to keyword.
     *
     * @param searchKey is a key that used for searching a comments.
     * @return set of a Tweet objects as a list.
     */
    public static List<Tweet> getTweets(String searchKey) throws TwitterException {
        //Connecting to a twitter app. You have to use your Consumer Keys and Access Tokens !!!!
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("*****")
                .setOAuthConsumerSecret("********")
                .setOAuthAccessToken("***********")
                .setOAuthAccessTokenSecret("**********");

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = tf.getInstance();
        List<Tweet> tweetList = new ArrayList<Tweet>();
        try {
            //ToDo: need to improvment on query
            Query query = new Query("(#" + searchKey + ") AND ((good) OR (bad)) exclude:retweets exclude:links");
            query.count(10);
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            double reviewPoint;
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            for (Status tweet : tweets) {
                System.out.println("@" + tweet.getUser().getScreenName() + " [id]: " + tweet.getId() + " : " + tweet.getText() + "[date]: " + tweet.getCreatedAt() + "\n");
                // ToDo: It is just for test, need to improve reviewPoint calculation
                reviewPoint = Math.random() * 100;
                Tweet newTweet = new Tweet(tweet.getId(), tweet.getText(), tweet.getUser().getScreenName(), df.format(tweet.getCreatedAt()), reviewPoint);
                tweetList.add(newTweet);
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        return tweetList;
    }
}
