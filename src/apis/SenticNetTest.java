package apis;

public class SenticNetTest {
    public static void main(String args[]) {

        SenticNet sentic = new SenticNet();
        double result = sentic.calculateReviewPoint("my brand new iphonex rocks! its really awesome!");
        System.out.println(result);
    }

}
