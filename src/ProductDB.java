import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class ProductDB {

    private MongoClient mongoClient;
    private MongoDatabase products;
    private MongoCollection mobilePhones;
    private MongoCollection laptops;
    private MongoCollection cameras;

    public ProductDB(){
        mongoClient = new MongoClient();
        products = mongoClient.getDatabase("Products");
        mobilePhones = products.getCollection("Mobile Phone");
        laptops = products.getCollection("Laptops");
        cameras = products.getCollection("Cameras");
    }

    public void instertToDB(MobilePhone mobilePhone){
        mobilePhones.insertOne(mobilePhone.toDocument());
    }

    public void instertToDB(Laptop laptop){
        laptops.insertOne(laptop.toDocument());
    }

    public void instertToDB(Camera camera){
        cameras.insertOne(camera.toDocument());
    }

    //public static final Document toDocument(Refrigerator Refrigerator){}

    //public static final Document toDocument(WashingMachine washingMachine){}

    //public static final Document toDocument(Dishwasher dishwasher){}

    //public static final Document toDocument(Car car){}

    //public static final Document toDocument(Motorcycle motorcycle){}

    //public static final Document toDocument(Bicycle bicycle){}

    public static void main( String args[] ) {
        //MongoClient mongoClient = new MongoClient();
        //MongoDatabase products = mongoClient.getDatabase("Products");
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