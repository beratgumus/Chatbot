import apis.SenticNet;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import strategyPattern.Calculation;
import strategyPattern.CalculationStrategy1;

import static org.junit.Assert.*;


public class SenticNetTest {
    Calculation calculationStrategy1;

    //    @Rule
//    public Timeout globalTimeOut = Timeout.millis(800);
    @Before
    public void setUp() throws Exception {
        calculationStrategy1= new CalculationStrategy1();
    }

    @Test // check the values of word from senticNet is true
    public void getSenticNetValue() throws Exception {
        double result = calculationStrategy1.calculate("good");
        assertEquals(0.664,result,0.00);
    }

    @Test // check the values of tweets from senticNet is true
    public void calculateReviewPoint() throws Exception {

        double result = calculationStrategy1.calculate("Have you noticed iPhone X owners hold their new phones like it is made of nitro glycerine? #iPhoneX");
        assertEquals(2.195,result,0.00);
    }
    
}