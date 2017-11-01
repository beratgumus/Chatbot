
public class SenticNetTest {
    public static void main(String args[]) {


        SenticNet sentic = new SenticNet();
        double result = sentic.calculateReviewPoint("berat gumus bekir");
        System.out.println(result);
    }

}
