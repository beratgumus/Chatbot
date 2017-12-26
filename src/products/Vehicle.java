package products;

import org.bson.Document;

public abstract class Vehicle extends Product {

    private String vehicleType;
    private int power;
    private String fuelType;


    public Vehicle(String id, String brand, String model, Double price, String type, Double height, Double width, Double depth, Double weight, Double reviewPoint1,Double reviewPoint2, int power, String fuelType) {
        super(id, brand, model, price, "Vehicle", type, height, width, depth, weight, reviewPoint1,reviewPoint2);
        this.vehicleType = type;
        this.power = power;
        this.fuelType = fuelType;
    }

    public Vehicle(String brand, String model, Double price, String type, Double height, Double width, Double depth, Double weight, int power, String fuelType) {
        super(brand, model, price, "Vehicle" ,type, height, width, depth, weight);
        this.vehicleType = type;
        this.power = power;
        this.fuelType = fuelType;
    }

    @Override
    public Document toDocument(){
        return super.toDocument()
                .append("Vehicle Type", vehicleType)
                .append("Power",power)
                .append("Fuel Type", fuelType);
    }

    @Override
    public String toString(){
        return super.toString() + "Power: " + power + "HP \nFuel Type: " + fuelType + "\n";
    }
}