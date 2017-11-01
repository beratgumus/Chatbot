import org.bson.Document;

public abstract class ConsumerElectronics extends Product {

    private Double screenSize;
    private int storageSize;

    public ConsumerElectronics(int id, String brand, String model, Double price, String type, Double height, Double width, Double depth, int weight, Double screenSize, int storageSize) {
        super(id, brand, model, price, "Consumer Electronics", type , height, width, depth, weight);
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

    public  Document toDocument(ConsumerElectronics consumerElectronics){
        return super.toDocument(consumerElectronics)
                .append("Screen Size", consumerElectronics.getScreenSize())
                .append("Storage Size", consumerElectronics.getStorageSize());
    }

    @Override
    public String toString() {
        return super.toString() + " --ConsumerElectronics [screenSize=" + screenSize + "″" + ", storageSize" + storageSize + "GB" + ", storageSize" + "]";
    }
}