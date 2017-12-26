package products;

import org.bson.Document;

public abstract class ConsumerElectronics extends Product {

    private Double screenSize;
    private int storageSize;

    public ConsumerElectronics(String id, String brand, String model, Double price, String type, Double height, Double width, Double depth, Double weight, Double reviewPoint1,Double reviewPoint2, Double screenSize, int storageSize) {
        super(id, brand, model, price, "Consumer Electronics", type , height, width, depth, weight, reviewPoint1,reviewPoint2);
        this.screenSize = screenSize;
        this.storageSize = storageSize;
    }

    public ConsumerElectronics(String brand, String model, Double price, String type, Double height, Double width, Double depth, Double weight, Double screenSize, int storageSize) {
        super(brand, model, price, "Consumer Electronics", type , height, width, depth, weight);
        this.screenSize = screenSize;
        this.storageSize = storageSize;
    }

    @Override
    public  Document toDocument(){
        return super.toDocument()
                .append("Screen Size", screenSize)
                .append("Storage Size", storageSize);
    }

    @Override
    public String toString() {
        return super.toString() + "Screen Size : " + screenSize + "\"" + "\nStorage " + storageSize + "GB" + "\n";
    }
}