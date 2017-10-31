import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TwitterAPI {

    public static List<Tweet> getTweets(String searchKey) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("*****")
                .setOAuthConsumerSecret("********")
                .setOAuthAccessToken("***********")
                .setOAuthAccessTokenSecret("**********");

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = tf.getInstance();
        List<Tweet> tweetList = new ArrayList<Tweet>();
        try {
            Query query = new Query("(#"+searchKey+") AND ((good) OR (bad)) exclude:retweets exclude:links");
            query.count(10);
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();

            double reviewPoint=0.00;
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            query.count(10);

            for (Status tweet : tweets) {
                System.out.println("@" + tweet.getUser().getScreenName() + " [id]: "+tweet.getId() + " : " + tweet.getText()+"[date]: "+tweet.getCreatedAt()+"\n");
                reviewPoint=(double) (Math.random()*100);
                Tweet newTweet = new Tweet(tweet.getId(),tweet.getText(),tweet.getUser().getScreenName(),df.format(tweet.getCreatedAt()),reviewPoint);
                tweetList.add(newTweet);
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }

       return tweetList;
    }

}
