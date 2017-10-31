import twitter4j.TwitterException;

import java.util.List;

public class twitterApiTest {

    public static void main (String args[]) throws TwitterException {
        List<Tweet> tweetss;
        tweetss=TwitterAPI.getTweets("iPhoneX");

        for (Tweet tweet: tweetss) {
            System.out.println(tweet);
        }
    }
}
