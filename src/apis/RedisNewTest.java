package apis;

import java.util.*;

public class RedisNewTest {

    public static void main(String[] args) {

//        Jedis db = new Jedis("localhost");

        //check whether server is running or not
//        System.out.println("Server is running: "+ db.ping());

        Redis db = new Redis();

        List<Tweet> list = db.getTweetByKeyword("iPhoneX");
        for (Tweet tweet : list) {
            //System.out.println(tweet.toString());
            System.out.println(tweet.getText());
        }
//        boolean succesful = db.addNewTweet("iPhoneX",newTweet);
//        if (succesful)
//            System.out.println("Tweet başarıyla eklendi");
//
//        Map tweet2  = db.getTweetById(1);
//        System.out.println(tweet2.get("text"));
//        System.out.println("Author: " + tweet2.get("user"));

        //List<Tweet> allTweets = db.getAllTweets();

        //  System.out.println(allTweets.get(1).getText() + " - Author: " +allTweets.get(1).getUser());

    }
}
