package products;

import org.bson.Document;

import java.util.HashMap;

public class Motorcycle extends Vehicle {
    private boolean windshield;
    private boolean carrierBox;

    public Motorcycle(String id, String brand, String model, Double price, Double height, Double width, Double depth, Double weight, Double reviewPoint, int power, String fuelType, boolean windshield, boolean carrierBox) {
        super(id, brand, model, price, "Motorcycle", height, width, depth, weight, reviewPoint, power, fuelType);
        this.windshield = windshield;
        this.carrierBox = carrierBox;
    }

    public Motorcycle(String brand, String model, Double price, Double height, Double width, Double depth, Double weight, int power, String fuelType, boolean windshield, boolean carrierBox) {
        super(brand, model, price, "Motorcycle", height, width, depth, weight, power, fuelType);
        this.windshield = windshield;
        this.carrierBox = carrierBox;
    }

    @Override
    public Document toDocument(){
        return super.toDocument()
                .append("Wind Shield", windshield)
                .append("CarrierBox", carrierBox);
    }

    @Override
    public String toString(){
        //convert boolean fields to readable format
        HashMap<Boolean,String> boolToStr = new HashMap<>();
        boolToStr.put(true, "Available");
        boolToStr.put(false, "Not Available");

        return super.toString() + "Windshield: " + boolToStr.get(windshield) + "\nCarrier Box: " + boolToStr.get(carrierBox) + "\n";
    }
}
