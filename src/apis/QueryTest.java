package apis;

import twitter4j.TwitterException;

import java.util.*;

public class QueryTest {

    public static void main(String[] args) {

        TwitterAPI testQuery = new TwitterAPI();
        try {
            testQuery.getReviewPoint("iPhoneX");
        } catch (TwitterException e) {

        }
        List<Tweet> tweetList=testQuery.getTweetList();
        for (Tweet tweet: tweetList) {
            System.out.println(tweet);
        }
    }
}
