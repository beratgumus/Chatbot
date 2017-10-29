public abstract class MajorAppliance extends Product {

    private int capacity;

    public MajorAppliance(int id, String brand, String model, Double price, Double height, Double width, Double depth, int weight, int capacity){
        super(id, brand, model, price, "Major Appliance", height, width, depth, weight);
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

}
