package strategyPattern;


import apis.SenticNet;
import twitter4j.Status;

import java.util.HashMap;

public class CalculationStrategy1 implements Calculation {

    @Override
    public double calculate(Status tweet) {

        return calculate(tweet.getText());

    }

    @Override
    public double calculate(String tweetText) {

        SenticNet sN = SenticNet.getInstance();
        HashMap<String, Double> data= sN.getAllSenticNetHashMap();

        Double sum = 0.0;
        //replaces all special characters (like # ! ? ...)
        tweetText = tweetText.replaceAll("[^a-zA-Z ]+", "");

        String[] words = tweetText.split(" ");
        for (String word : words) {
            word = word.toLowerCase();
            Double polarity = data.get(word);
            if (polarity != null) {
                sum += polarity;
                // System.out.println(word + " : " + polarity);
            }
        }
        return sum;
    }
}
