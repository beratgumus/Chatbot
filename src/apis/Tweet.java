package apis;

import java.util.HashMap;
import java.util.Map;

public class Tweet implements Comparable<Tweet> {
    private long id;
    private String text;
    private String user;
    private String timeSpan;
    private double reviewPoint;

    public Tweet(long id, String text, String user, String timeSpan, double reviewPoint) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.timeSpan = timeSpan;
        this.reviewPoint = reviewPoint;
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

    public double getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(double reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    public String serialize() {
        return id + "♦" + text + "♦" + user + "♦" + timeSpan + "♦" + reviewPoint;
    }

    public String toString() {
        return "[" + reviewPoint + "] " + user + ": " + text + " - " + timeSpan;
    }

    @Override
    public int compareTo(Tweet tweet) {
        if (this.reviewPoint == tweet.reviewPoint)
            return 0;
        else if (this.reviewPoint > tweet.reviewPoint)
            return -1;
        else
            return 1;
    }
}
