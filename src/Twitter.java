import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class Twitter {

    public static void main(String[] args) throws TwitterException {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("****************")
                .setOAuthConsumerSecret("********")
                .setOAuthAccessToken("*****************")
                .setOAuthAccessTokenSecret("*******************");

//        TwitterFactory tf = new TwitterFactory(cb.build());
//        twitter4j.Twitter twitter =tf.getInstance();
//
//        List<Status> status = twitter.getHomeTimeline();
//        for (Status s : status) {
//            System.out.println(s.getUser().getName() + "-----"+s.getText());
//        }
        twitter4j.Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        try {
            Query query = new Query("(#iPhoneX) AND ((good) OR (bad) OR (love) OR (like) OR (hate) OR (worst) OR (meh))");
//            query.count();
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();

            for (Status status : tweets) {
                System.out.println("@" + status.getUser().getScreenName() + " : " + status.getText() + " : " + " \n");
            }
        } catch (TwitterException te) {
            te.printStackTrace();
        }

    }
}
