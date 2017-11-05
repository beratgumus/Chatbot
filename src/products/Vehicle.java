package products;

import org.bson.Document;

public abstract class Vehicle extends Product {

    private String vehicleType;
    private int power;
    private String fuelType;


    public Vehicle(String id, String brand, String model, Double price, String type, Double height, Double width, Double depth, int weight, Double reviewPoint, String vehicleType, int power, String fuelType) {
        super(id, brand, model, price, "Vehicle", type, height, width, depth, weight, reviewPoint);
        this.vehicleType = vehicleType;
        this.power = power;
        this.fuelType = fuelType;
    }

    public Vehicle(String brand, String model, Double price, String type, Double height, Double width, Double depth, int weight, String vehicleType, int power, String fuelType) {
        super(brand, model, price, "Vehicle" ,type, height, width, depth, weight);
        this.vehicleType = vehicleType;
        this.power = power;
        this.fuelType = fuelType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public Document toDocument(){
        return super.toDocument()
                .append("Vehicle Type", getVehicleType())
                .append("Power", getPower())
                .append("Fuel Type", getFuelType());
    }

    @Override
    public String toString(){
        return super.toString() + "Power: " + power + "HP \nFuel Type: " + fuelType + "\n";
    }
}