import twitter4j.TwitterException;

import java.util.List;

public class twitterApiTest {

    public static void main(String args[]) throws TwitterException {
        List<Tweet> tweetss;
        TwitterAPI twitterAPI = new TwitterAPI();
        tweetss = twitterAPI.getTweets("iPhoneX");

        for (Tweet tweet : tweetss) {
            System.out.println(tweet);
        }
    }
}
