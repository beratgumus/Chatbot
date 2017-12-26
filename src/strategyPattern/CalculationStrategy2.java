package strategyPattern;


public class CalculationStrategy2 implements Calculation{

    @Override
    public double calculate(String tweetText) {
        return 15;
    }
}
