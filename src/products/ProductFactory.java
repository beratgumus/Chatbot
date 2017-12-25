package products;

public class ProductFactory {

    private ProductFactory() {

    }

    /**
     * Overload for creating a MobilePhone
     */
    public static Product createProduct(String brand, String model, Double price, Double height, Double width, Double depth, Double weight, Double screenSize, int storageSize, int cameraResolution, String os, int ramSize){
        return new MobilePhone(brand, model, price, height, width, depth, weight, screenSize, storageSize, cameraResolution, os, ramSize);
    }

    /**
     * Overload for creating a Laptop
     */
    public static Product createProduct(String brand, String model, Double price, Double height, Double width, Double depth, Double weight, Double screenSize, int storageSize, int ramSize, String cpuModel, String os){
        return new Laptop(brand, model, price, height, width, depth, weight, screenSize, storageSize, ramSize, cpuModel, os);
    }

    /**
     * Overload for creating a Car
     */
    public static Product createProduct(String brand, String model, Double price, Double height, Double width, Double depth, Double weight, int power, String fuelType, int numberOfSeats, String airConditionerType){
        return new Car(brand, model, price, height, width, depth, weight, power, fuelType, numberOfSeats, airConditionerType);
    }

    /**
     * Overload for creating a Motorcycle
     */
    public static Product createProduct(String brand, String model, Double price, Double height, Double width, Double depth, Double weight, int power, String fuelType, boolean windshield, boolean carrierBox) {
        return new Motorcycle(brand, model, price, height, width, depth, weight, power, fuelType, windshield, carrierBox);
    }

    /**
     * Overload for creating a Refrigerator
     */
    public static Product createProduct(String brand, String model, Double price, Double height, Double width, Double depth, Double weight, int capacity, String energyEfficiency, String refrigeratorType, boolean iceMaker, boolean frostFree, boolean doorOpenAlarm) {
        return new Refrigerator(brand, model, price, height, width, depth, weight, capacity, energyEfficiency, refrigeratorType, iceMaker, frostFree, doorOpenAlarm);
    }

}
