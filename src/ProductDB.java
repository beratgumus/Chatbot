import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class ProductDB {

    public static final Document toDocument(MobilePhone mobilePhone){
        return new Document("_id", mobilePhone.getId())
                .append("Brand", mobilePhone.getBrand())
                .append("Model", mobilePhone.getModel())
                .append("Price", mobilePhone.getPrice())
                .append("Height", mobilePhone.getHeight())
                .append("Width", mobilePhone.getWidth())
                .append("Depth", mobilePhone.getDepth())
                .append("Weight", mobilePhone.getWeight())
                .append("Screen Size", mobilePhone.getScreenSize())
                .append("Storage Size", mobilePhone.getStorageSize())
                .append("Camera Resolution", mobilePhone.getCameraResolution())
                .append("OS", mobilePhone.getOs())
                .append("RAM Size", mobilePhone.getRamSize());
    }

    public static final Document toDocument(Laptop laptop){
        return new Document("_id", laptop.getId())
                .append("Brand", laptop.getBrand())
                .append("Model", laptop.getModel())
                .append("Price", laptop.getPrice())
                .append("Height", laptop.getHeight())
                .append("Width", laptop.getWidth())
                .append("Depth", laptop.getDepth())
                .append("Weight", laptop.getWeight())
                .append("Screen Size", laptop.getScreenSize())
                .append("Storage Size", laptop.getStorageSize())
                .append("RAM Size", laptop.getRamSize())
                .append("CPU Model", laptop.getCpuModel())
                .append("OS", laptop.getOs());
    }

    public static final Document toDocument(Camera camera){
        return new Document("_id", camera.getId())
                .append("Brand", camera.getBrand())
                .append("Model", camera.getModel())
                .append("Price", camera.getPrice())
                .append("Height", camera.getHeight())
                .append("Width", camera.getWidth())
                .append("Depth", camera.getDepth())
                .append("Weight", camera.getWeight())
                .append("Screen Size", camera.getScreenSize())
                .append("Storage Size", camera.getStorageSize())
                .append("Video Resolution", camera.getVideoResolution())
                .append("Image Resolution", camera.getImageResolution())
                .append("ISO", camera.getIso());
    }

    //public static final Document toDocument(Refrigerator Refrigerator){}

    //public static final Document toDocument(WashingMachine washingMachine){}

    //public static final Document toDocument(Dishwasher dishwasher){}

    //public static final Document toDocument(Car car){}

    //public static final Document toDocument(Motorcycle motorcycle){}

    //public static final Document toDocument(Bicycle bicycle){}

    public static void main( String args[] ) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase products = mongoClient.getDatabase("Products");
        //products.getCollection("Mobile Phone").insertOne(toDocument(new MobilePhone()));
        /*
        products.createCollection("Mobile Phones");
        products.createCollection("Laptops");
        products.createCollection("Cameras");
        products.createCollection("Refrigerators");
        products.createCollection("Washing Machines");
        products.createCollection("Dishwashers");
        products.createCollection("Cars");
        products.createCollection("Motorcycles");
        products.createCollection("Bicycles");
        //MongoCollection collection = products.getCollection("Cameras");
*/

    }
}