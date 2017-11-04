package products;

public class TestForComp {

    public static void main(String[] args) {

        MobilePhone mp1 = new MobilePhone("MyPhone", "iPhone8", 800.0, 6.25, 1.01, 2.23, 98, 5.2, 16, 13, "Android 7.0", 2);
        MobilePhone mp2 =  new MobilePhone("Apple", "IphoneX ", 372253.0, 7.12, 5.23, 2.22, 111, 5.325, 16, 13, "iOS 11", 2);

        int result =mp1.compareTo(mp2);
        System.out.println("RETURN VALUE OF compareTo METHOD:  "+result);
    }
}