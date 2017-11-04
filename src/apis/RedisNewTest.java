package apis;

import java.util.*;

public class RedisNewTest {

    public static void main(String[] args) {

        Redis db = new Redis();

        List<Tweet> list = db.getTweetByKeyword("iPhoneX");
        for (Tweet tweet : list) {
            //System.out.println(tweet.toString());
            System.out.println(tweet.getText());
        }


    }
}
