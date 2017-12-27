import apis.Redis;
import apis.SenticNet;
import apis.Tweet;
import org.junit.Test;
import strategyPattern.Calculation;
import strategyPattern.CalculationStrategy1;
import strategyPattern.CalculationStrategy2;

import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.*;


public class RedisTest {
    private Redis redis = Redis.getInstance();

    @Test // check the review point calculation of all tweets for specified product.
    public void getTweetsByKeyword() throws Exception {
        String keyword ="GalaxyNote8";
        List<Tweet> tweetList = redis.getTweetsByKeyword(keyword);
        Calculation calculationStrategy1 = new CalculationStrategy1();

        for (Tweet tweet : tweetList){
           double storedReviewPoint = tweet.getcalStrategy1();
            double actual = calculationStrategy1.calculate(tweet.getText());

           assertEquals(storedReviewPoint,actual,0.000);
        }
    }
    @Test
    public void sentimentAnalysis1()throws Exception{

        String keyword ="ModelS";

        List<Tweet> tweetList = redis.getTweetsByKeyword(keyword);
        Calculation calculationStrategy1 = new CalculationStrategy1();

        double sum =0;
        for (Tweet tweet : tweetList){
            double currentTweetValue = calculationStrategy1.calculate(tweet.getText());
            sum += currentTweetValue;
        }
        double result = sum / tweetList.size();
        assertEquals(1.1599999999999997,result,0.000);
    }

}