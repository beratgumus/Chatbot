package products;

import org.bson.Document;

public class MobilePhone extends ConsumerElectronics {

    private int cameraResolution;
    private String os;
    private int ramSize;

    public MobilePhone(String id, String brand, String model, Double price, Double height, Double width, Double depth, Double weight, Double reviewPoint1,Double reviewPoint2, Double screenSize, int storageSize, int cameraResolution, String os, int ramSize) {
        super(id, brand, model, price, "Mobile Phone", height, width, depth, weight, reviewPoint1,reviewPoint2, screenSize, storageSize);
        this.cameraResolution = cameraResolution;
        this.os = os;
        this.ramSize = ramSize;
    }

    public MobilePhone(String brand, String model, Double price, Double height, Double width, Double depth, Double weight, Double screenSize, int storageSize, int cameraResolution, String os, int ramSize) {
        super(brand, model, price, "Mobile Phone", height, width, depth, weight, screenSize, storageSize);
        this.cameraResolution = cameraResolution;
        this.os = os;
        this.ramSize = ramSize;
    }

    public Document toDocument(){
        return super.toDocument()
                .append("Camera Resolution", cameraResolution)
                .append("OS", os)
                .append("RAM Size", ramSize);
    }

    @Override
    public String toString(){
        return super.toString() + "Camera: " + cameraResolution + " MP" +
                "\nOperating System: " + os + "\nRAM: " + ramSize + "GB" + "\n";
    }

    
}