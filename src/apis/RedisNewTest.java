package apis;

import java.util.*;

public class RedisNewTest {

    public static void main(String[] args) {

        Redis db = new Redis();

//        List<Tweet> tweetList = new ArrayList<>();
//        tweetList.add(new Tweet(135135, "my new #iphonex is awesome", "Bekir", "10/11/2010", 3.25));
//        tweetList.add(new Tweet(335634, "I hate #iphonex.  It's not worth 1999$. My miss my old phone from now.", "Berat", "6/12/2012", -1.54));
//        tweetList.add(new Tweet(413414, "Well, #iphonex is OK but not best.", "Ramazan", "4/4/2013", 1.71));
//
//        db.addNewTweets("iphonex", tweetList);


        List<Tweet> list = db.getTweetsByKeyword("iphonex");
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
