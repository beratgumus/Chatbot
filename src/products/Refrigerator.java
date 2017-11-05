package products;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Refrigerator extends MajorAppliance {

    private String refrigeratorType;
    private boolean iceMaker;
    private boolean frostFree;
    private boolean doorOpenAlarm;

    public Refrigerator(String id, String brand, String model, Double price, Double height, Double width, Double depth, int weight, Double reviewPoint, int capacity, String energyEfficiency, String refrigeratorType, boolean iceMaker, boolean frostFree, boolean doorOpenAlarm) {
        super(id, brand, model, price, "Refrigerator" , height, width, depth, weight, reviewPoint, capacity, energyEfficiency);
        this.refrigeratorType = refrigeratorType;
        this.iceMaker = iceMaker;
        this.frostFree = frostFree;
        this.doorOpenAlarm = doorOpenAlarm;
    }

    public Refrigerator(String brand, String model, Double price, Double height, Double width, Double depth, int weight, int capacity, String energyEfficiency, String refrigeratorType, boolean iceMaker, boolean frostFree, boolean doorOpenAlarm) {
        super(brand, model, price, "Refrigerator" ,height, width, depth, weight, capacity, energyEfficiency);
        this.refrigeratorType = refrigeratorType;
        this.iceMaker = iceMaker;
        this.frostFree = frostFree;
        this.doorOpenAlarm = doorOpenAlarm;
    }

    public String getRefrigeratorType() {
        return refrigeratorType;
    }

    public void setRefrigeratorType(String refrigeratorType) {
        this.refrigeratorType = refrigeratorType;
    }

    public boolean isIceMaker() {
        return iceMaker;
    }

    public void setIceMaker(boolean iceMaker) {
        this.iceMaker = iceMaker;
    }

    public boolean isFrostFree() {
        return frostFree;
    }

    public void setFrostFree(boolean frostFree) {
        this.frostFree = frostFree;
    }

    public boolean isDoorOpenAlarm() {
        return doorOpenAlarm;
    }

    public void setDoorOpenAlarm(boolean doorOpenAlarm) {
        this.doorOpenAlarm = doorOpenAlarm;
    }

    @Override
    public Document toDocument(){
        return super.toDocument()
                .append("Refrigerator Type", getCapacity())
                .append("Ice Maker", isIceMaker())
                .append("Frost Free", isFrostFree())
                .append("Door Open Alarm", isDoorOpenAlarm());
    }

    @Override
    public String toString(){
        //convert boolean fields to readable format
        HashMap<Boolean,String> boolToStr = new HashMap<>();
        boolToStr.put(true, "Available");
        boolToStr.put(false, "Not Available");

        return super.toString() + "Type: " + refrigeratorType + "\nIce Maker: " + boolToStr.get(iceMaker)
                + "\nFrost Free: " + boolToStr.get(frostFree) + "\nDoor Open Alarm " + boolToStr.get(doorOpenAlarm)  + "\n";
    }
}