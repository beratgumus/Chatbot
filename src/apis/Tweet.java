package apis;

import java.util.HashMap;
import java.util.Map;

public class Tweet implements Comparable<Tweet> {
    private long id;
    private String text;
    private String user;
    private String timeSpan;
    private double calStrategy1;
    private double calStrategy2;
    private static int calculationMod = 1;

    public Tweet(long id, String text, String user, String timeSpan, double calStrategy1, double calStrategy2) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.timeSpan = timeSpan;
        this.calStrategy1 = calStrategy1;
        this.calStrategy2 = calStrategy2;
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

    public double getcalStrategy1() {
        return calStrategy1;
    }

    public double getcalStrategy2() {
        return calStrategy2;
    }


    public void setcalStrategy1(double calStrategy1) {
        this.calStrategy1 = calStrategy1;
    }

    public void setcalStrategy2(double calStrategy2) {
        this.calStrategy2 = calStrategy2;
    }

    /**
     * Prepares tweet to Redis.io insertation.
     *
     * @return serialized string
     */
    public String serialize() {
        return id + "♦" + text + "♦" + user + "♦" + timeSpan + "♦" + calStrategy1 + "♦" + calStrategy2;
    }

    public String toString() {
        return "Tweet Strategy1 Point: " + calStrategy1 +
                "Tweet Strategy2 Point: " + calStrategy2 +
                "\nUsername: " + user +
                "\nTweet Text: " + text +
                "\nDate:  " + timeSpan + "\n";
    }

    public static void setCalculationMod(int mod) {
        calculationMod = mod;
    }

    @Override
    public int compareTo(Tweet tweet) {
        if (calculationMod == 1) {
            if (this.calStrategy1 == tweet.calStrategy1)
                return 0;
            else if (this.calStrategy1 > tweet.calStrategy1)
                return -1;
            else
                return 1;
        } else {
            if (this.calStrategy2 == tweet.calStrategy2)
                return 0;
            else if (this.calStrategy2 > tweet.calStrategy2)
                return -1;
            else
                return 1;
        }
    }
}
