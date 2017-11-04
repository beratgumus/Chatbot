package apis;

import java.util.*;

public class Main {

    public static void main(String[] args) {

//        Jedis db = new Jedis("localhost");

        //check whether server is running or not
//        System.out.println("Server is running: "+ db.ping());

        RedisNew db = new RedisNew();

        Tweet newTweet = new Tweet(1, "I am setting up twitter!", "BekirUzn", "1509297291688", 0.0);
//        Tweet newTweet = new Tweet("Hey! here is my second tweet.", "BekirUzn" , "1509313291147" , 0.0);
//        Tweet newTweet = new Tweet("New #iphonex is awesome. I love it!", "BekirUzn" , "1509321245641" , 3.7);


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
