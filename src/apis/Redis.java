package apis;

import redis.clients.jedis.Jedis;
import java.util.*;


/**
 * Wrapper class for Redis.io database.
 * Generated for reusability
 *
 */
public class Redis {
    private static final String redisHost = "localhost";
    private Jedis db;

    public Redis() {
        db = new Jedis(redisHost);
    }

    /**
     * Gets the tweet from the database
     * @param id tweet id
     * @return tweet
     */
    public Map<String, String> getTweetById(int id){
        Map tweet =  db.hgetAll("tweet:"+id);
        return tweet;
    }

    /**
     * Retrieves all tweets from database
     * @return List of tweets
     */
    public List<Tweet> getAllTweets(){
        //get the last inserted tweet id from database
        int lastTweetId = Integer.parseInt(db.get("next_tweet_id")) - 1;

        //we will fill this list with tweets
        List<Tweet> allTweets = new ArrayList<Tweet>();

        //this loop gets all tweets from database and adds them to List
        for (int i = 1; i <= lastTweetId; i++){
            Map<String,String> tempTweetMap =  getTweetById(i);
            if (tempTweetMap.size() == 0){
                // tweet with given is not exists on database. continue looping
                continue;
            }

            //create a real Tweet object with information retrieved from database
            Tweet tempTweet = new Tweet(tempTweetMap);
            tempTweet.setId(i);
            allTweets.add(tempTweet);
        }
        return allTweets;
    }

    /**
     * Adds given tweet to the database
     * @param newTweet source tweet
     * @return returns true if operation was succesful
     */
    public boolean addNewTweet( Map<String, String> newTweet ){
        if (!db.exists("next_tweet_id")){
            db.set("next_tweet_id", "1");
        }
        int id = Integer.parseInt(db.get("next_tweet_id"));
        String ret = db.hmset("tweet:" + id, newTweet);

        if (ret.equals("OK")){
            //tweet inserted succesfully
            db.incr("next_tweet_id");
            return true;
        } else {
            //error
            return false;
        }
    }

    /**
     * Adds given tweet to the database
     * @param newTweet source tweet
     * @return returns true if operation was succesful
     */
    public boolean addNewTweet( Tweet newTweet){
        return addNewTweet(newTweet.toMap());
    }

   /**
    * USE WITH CAUTION! Deletes all the keys of the currently selected DB.
    * @see <a href="https://redis.io/commands/flushdb">FLUSHDB</a>
    */
    public void reset() {
        db.flushDB();
    }

    /**
     * Closes database connection. Use this after you are done with object
     */
    public void close(){
        db.close();
    }


}
