public abstract class MajorAppliance extends Product {

    private int capacity;

    public MajorAppliance(int id, String brand, String model, Double price, int capacity){
        super(id, brand, model, price, "Major Appliance");
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

}
