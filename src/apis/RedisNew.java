package apis;

import redis.clients.jedis.Jedis;

import java.util.*;


/**
 * Wrapper class for Redis.io database.
 * Generated for reusability
 */
public class RedisNew {
    private static final String redisHost = "localhost";
    private Jedis db;

    public RedisNew() {
        db = new Jedis(redisHost);
    }

    /**
     * Gets the tweet from the database
     *
     * @param id tweet id
     * @return tweet
     */
    public Map<String, String> getTweetById(int id) {
        Map tweet = db.hgetAll("tweet:" + id);
        return tweet;
    }

    /**
     * Retrieves all tweets from database
     *
     * @return List of tweets
     */
    public List<Tweet> getAllTweets() {
        //get the last inserted tweet id from database


        //we will fill this list with tweets
        List<Tweet> allTweets = new ArrayList<Tweet>();


        return allTweets;
    }

    /**
     * Adds given tweet to the database
     *
     * @param newTweet source tweet
     * @return returns true if operation was succesful
     */
//    public boolean addNewTweet(String keyword, List<Map<String, String>> hashedTweetList) {
//
//        return true;
//    }

    /**
     * Adds given tweet to the database
     *
     * @param newTweet source tweet
     * @return returns true if operation was succesful
     */
//    public boolean addNewTweet(String keyword, Tweet newTweet) {
//        return addNewTweet(keyword, newTweet.toMap());
//    }
    public boolean addNewTweet(String keyword, List<Tweet> tweetList) {
        List<Map<String, String>> hashedTweetList = new ArrayList<Map<String, String>>();
        for (Tweet tweet : tweetList) {
            hashedTweetList.add(tweet.toMap());
        }
        for (Map<String, String> tweet : hashedTweetList) {
            if (!db.exists("next_tweet_id")) {
                db.set("next_tweet_id", "1");
            }
            int id = Integer.parseInt(db.get("next_tweet_id"));
            String ret = db.hmset(keyword + ": " + id, tweet);
            db.incr("next_tweet_id");
        }
        return true;
    }

    /**
     * USE WITH CAUTION! Deletes all the keys of the currently selected DB.
     *
     * @see <a href="https://redis.io/commands/flushdb">FLUSHDB</a>
     */
    public void reset() {
        db.flushDB();
    }


}
