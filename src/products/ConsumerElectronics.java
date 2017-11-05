package products;

import org.bson.Document;

public abstract class ConsumerElectronics extends Product {

    private Double screenSize;
    private int storageSize;

    public ConsumerElectronics(String id, String brand, String model, Double price, String type, Double height, Double width, Double depth, int weight, Double reviewPoint, Double screenSize, int storageSize) {
        super(id, brand, model, price, "Consumer Electronics", type , height, width, depth, weight, reviewPoint);
        this.screenSize = screenSize;
        this.storageSize = storageSize;
    }

    public ConsumerElectronics(String brand, String model, Double price, String type, Double height, Double width, Double depth, int weight, Double screenSize, int storageSize) {
        super(brand, model, price, "Consumer Electronics", type , height, width, depth, weight);
        this.screenSize = screenSize;
        this.storageSize = storageSize;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    @Override
    public  Document toDocument(){
        return super.toDocument()
                .append("Screen Size", getScreenSize())
                .append("Storage Size", getStorageSize());
    }

    @Override
    public String toString() {
        return super.toString() + "Screen Size : " + screenSize + "\"" + "\nStorage " + storageSize + "GB" + "\n";
    }
}