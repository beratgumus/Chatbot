package products;

import org.bson.Document;

public abstract class MajorAppliance extends Product {

    private int capacity;
    private String energyEfficiency;

    public MajorAppliance(String id, String brand, String model, Double price, String type, Double height, Double width, Double depth, int weight, Double reviewPoint, int capacity, String energyEfficiency) {
        super(id, brand, model, price, "Major Appliance", type, height, width, depth, weight, reviewPoint);
        this.capacity = capacity;
        this.energyEfficiency = energyEfficiency;
    }

    public MajorAppliance(String brand, String model, Double price, String type, Double height, Double width, Double depth, int weight, int capacity, String energyEfficiency) {
        super(brand, model, price, "Major Appliance", type, height, width, depth, weight);
        this.capacity = capacity;
        this.energyEfficiency = energyEfficiency;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public String getEnergyEfficiency() {
        return energyEfficiency;
    }

    public void setEnergyEfficiency(String energyEfficiency) {
        this.energyEfficiency = energyEfficiency;
    }

    @Override
    public Document toDocument(){
        return super.toDocument()
                .append("Capacity", capacity)
                .append("Energy Efficiency", energyEfficiency);
    }

    @Override
    public String toString(){
        return super.toString() + "Capacity: " + capacity + "\nEnergy Efficiency: " + energyEfficiency + "\n";
    }

}
