package apis;

import twitter4j.TwitterException;

import java.util.List;

public class twitterApiTest {

    public static void main(String args[]) throws TwitterException {

        TwitterAPI twitterAPI = new TwitterAPI();
        double review = twitterAPI.getReviewPoint("iPhoneX");
        System.out.println(review);

    }
}
