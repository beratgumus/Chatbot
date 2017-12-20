import apis.SenticNet;
import org.junit.Test;

import static org.junit.Assert.*;


public class SenticNetTest {
    @Test
    public void calculateReviewPoint() throws Exception {
        SenticNet SN = SenticNet.getInstance();
        double result = SN.calculateReviewPoint("good");
        assertEquals(0.664,result,0.00);
    }
    
}