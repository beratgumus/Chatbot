package apis;

import java.util.HashMap;
import java.util.Map;

public class Tweet {
    private long id;
    private String text;
    private String user;
    private String timespan;
    private double reviewPoint;

    public Tweet(long id, String text, String user, String timespan, double reviewPoint) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.timespan = timespan;
        this.reviewPoint = reviewPoint;
    }

    /**
     * Initialize Tweet object from Map object. Probably used when getting tweet from Redis only.
     * @param tweetFromDB Map object that contains tweet information
     */
    public Tweet( Map<String, String> tweetFromDB){
        this(Long.parseLong(tweetFromDB.get("id")),tweetFromDB.get("text"), tweetFromDB.get("user"),
                tweetFromDB.get("timespan"), Double.parseDouble(tweetFromDB.get("reviewPoint")) );
    }

    /**
     * Converts Tweet object to Map object. Probably used when inserting tweet to Redis only.
     * @return Converted Map object that contains tweet information
     */
    public Map<String, String> toMap(){
        Map<String, String> tweet = new HashMap<>();

        tweet.put("text", this.text);
        tweet.put("user", this.user);
        tweet.put("timespan", timespan);
        tweet.put("reviewPoint", Double.toString(reviewPoint));

        return tweet;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTimespan() {
        return timespan;
    }

    public void setTimespan(String timespan) {
        this.timespan = timespan;
    }

    public double getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(double reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

}