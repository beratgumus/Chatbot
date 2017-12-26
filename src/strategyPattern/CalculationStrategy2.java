package strategyPattern;


import twitter4j.Status;

public class CalculationStrategy2 implements Calculation{
    Calculation basicCalculation;

    @Override
    public double calculate(Status tweet) {

        basicCalculation = new CalculationStrategy1();
        double basicResult = basicCalculation.calculate(tweet.getText());

        int followersCount = tweet.getUser().getFollowersCount();
        int factor = followersCount/100;
        double percentage = (factor/10) + 1.00;
        return basicResult*percentage;
    }

    @Override
    public double calculate(String tweetText) {
        return 0;
    }
}
