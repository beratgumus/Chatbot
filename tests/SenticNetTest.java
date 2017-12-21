import apis.SenticNet;
import org.junit.Test;

import static org.junit.Assert.*;


public class SenticNetTest {
    private SenticNet SN = SenticNet.getInstance();

    @Test // check the values of word from senticNet is true
    public void getSenticNetValue() throws Exception {
        double result = SN.getSenticNetValue("good");
        assertEquals(0.664,result,0.00);
    }

    @Test // check the values of tweets from senticNet is true
    public void calculateReviewPoint() throws Exception {
        double result = SN.calculateReviewPoint("Have you noticed iPhone X owners hold their new phones like it is made of nitro glycerine? #iPhoneX");
        assertEquals(2.195,result,0.00);
    }
}