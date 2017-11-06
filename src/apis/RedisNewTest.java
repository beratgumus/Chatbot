package apis;

import java.util.*;

public class RedisNewTest {

    public static void main(String[] args) {

        Redis db = new Redis();

//        List<Tweet> tweetList = new ArrayList<>();
//        tweetList.add(new Tweet(135135, "Test tweet 1", "Bekir", "10/11/2010", 0.3));
//        tweetList.add(new Tweet(335634, "Test tweet 2", "Bekir", "6/12/2012", -0.5));
//        tweetList.add(new Tweet(413414, "Test tweet 3", "Bekir", "4/4/2013", 2.6));

//        db.addNewTweets("Test", tweetList);


        List<Tweet> list = db.getTweetsByKeyword("Test");
        if (list.size() == 0){
            System.out.println("No tweet found with given key.");
        } else {
            for (Tweet tweet : list) {
                //System.out.println(tweet.toString());
                System.out.println(tweet);
            }
        }

        System.out.println("\n");

    }
}
