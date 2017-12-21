import apis.Redis;
import apis.SenticNet;
import apis.Tweet;

import java.util.List;

public class FixRedisCalculation {

    public static void main(String[] args){
        Redis redis =Redis.getInstance();
        SenticNet sn =SenticNet.getInstance();
        String keyword ="iPhoneX";
        List<Tweet> tweetList = redis.getTweetsByKeyword(keyword);

        for (Tweet tweet : tweetList){
            double newValue =sn.calculateReviewPoint(tweet.getText());
            tweet.setReviewPoint(newValue);
        }
        tweetList.sort(Tweet::compareTo);
        redis.addNewTweets(keyword,tweetList);

    }
}
