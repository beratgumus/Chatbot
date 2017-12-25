import apis.Redis;
import apis.SenticNet;
import apis.Tweet;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class RedisTest {
    Redis redis = Redis.getInstance();
    SenticNet sn = SenticNet.getInstance();

    @Test // check the review point calculation of all tweets for specified product.
    public void getTweetsByKeyword() throws Exception {
        String keyword ="iPhoneX";
        List<Tweet> tweetList = redis.getTweetsByKeyword(keyword);
        for (Tweet tweet : tweetList){
           double storedReviewPoint = tweet.getReviewPoint();
           double actual = sn.calculateReviewPoint(tweet.getText());
           assertEquals(storedReviewPoint,actual,0.000);
        }
    }

}