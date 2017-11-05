package apis;

import redis.clients.jedis.Jedis;

import java.util.*;


/**
 * Wrapper class for Redis.io database.
 * Generated for re-usability
 */
public class Redis {
    private static final String redisHost = "localhost";
    private Jedis db;

    public Redis() {
        db = new Jedis(redisHost);
    }

    /**
     * @param keyword for getting tweets from redis
     * @return list of tweets according to keyword
     */
    public List<Tweet> getTweetByKeyword(String keyword) {
        List<Tweet> tweetList = new ArrayList<Tweet>();
        long size = db.llen(keyword);
        for (int i = 1; i < size; i++) {
            String tweetInfo = db.lindex(keyword, i);
            String[] parts = tweetInfo.split("♦");
            tweetList.add(new Tweet(Long.parseLong(parts[0]), parts[2], parts[1], parts[3], Double.parseDouble(parts[4])));
        }
        return tweetList;
    }

    /**
     * Adds given tweets to the redis
     *
     * @param tweetList source tweet list
     */

    public void addNewTweet(String keyword, List<Tweet> tweetList) {
        for (Tweet tweet : tweetList) {
            String value = "" + tweet.getId() + "♦" + tweet.getUser() + "♦" + tweet.getText() + "♦" + tweet.getTimeSpan() + "♦" + tweet.getReviewPoint();
            db.lpush(keyword, value);
        }
    }

    /**
     * USE WITH CAUTION! Deletes all the keys of the currently selected DB.
     *
     * @see <a href="https://redis.io/commands/flushdb">FLUSHDB</a>
     */
    public void reset() {
        db.flushDB();
    }

    /**
     * Closes database connection. Use this after you are done with object
     */
    public void close() {
        db.close();
    }


}
