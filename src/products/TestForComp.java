package products;

import java.util.ArrayList;
import java.util.List;

public class TestForComp {

    public static void main(String[] args) {
        MobilePhone mp1 = new MobilePhone("MyPhone", "galaxy s 7", 800.0, 6.25, 1.01, 2.23, 98, 5.2, 16, 13, "Android 7.0", 1);
        MobilePhone mp2 =  new MobilePhone("Apple", "I phone 3 ", 372253.0, 7.12, 5.23, 2.22, 111, 5.325, 16, 13, "iOS 11", 2);
        MobilePhone mp3 =  new MobilePhone("Apple", "I phone 5 ", 372253.0, 7.12, 5.23, 2.22, 111, 5.325, 16, 13, "iOS 11", 2);
        MobilePhone mp4 =  new MobilePhone("Apple", "I phone X ", 372253.0, 7.12, 5.23, 2.22, 111, 5.325, 16, 13, "iOS 11", 2);

        mp1.setReviewPoint(3.0);
        mp2.setReviewPoint(-1.6);
        mp3.setReviewPoint(4.0);
        mp4.setReviewPoint(2.98);


        int result = mp1.compareTo(mp2);
        System.out.println("RETURN VALUE OF compareTo METHOD:  "+result + "\n\n");

        List<Product> products = new ArrayList<>();
        products.add(mp1);
        products.add(mp2);
        products.add(mp3);
        products.add(mp4);

        System.out.println(" \nBefore sorting :");
        for (Product p : products){
            System.out.println(p.getModel() + " | " + p.getReviewPoint());
        }

        products.sort(Product::compareTo);

        System.out.println(" \nAfter sorting :");
        for (Product p : products){
            System.out.println(p.getModel() + " | " + p.getReviewPoint());
        }

    }
}