import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.model.Projections;
import org.bson.types.ObjectId;


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

    public List<Product> getDB(){
        List<Document> mobilePhoneCollection = new ArrayList<>();
        List<Document> laptopCollection = new ArrayList<>();
        List<Document> cameraCollection = new ArrayList<>();
        List<Product> productCollection = new ArrayList<>();

        mobilePhoneCollection = documentCollector(mobilePhones);
        laptopCollection = documentCollector(laptops);
        cameraCollection = documentCollector(cameras);

        for(Document product : mobilePhoneCollection){
            productCollection.add(toMobilePhone(product));
        }

        for(Document product : laptopCollection){
            productCollection.add(toLaptop(product));
        }

        for(Document product : cameraCollection){
            productCollection.add(toCamera(product));
        }

        return productCollection;
    }

    public List<Document> documentCollector(MongoCollection mongoCollection){
        List<Document> documentCollection = new ArrayList<>();

        MongoCursor<Document> cursor = mongoCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
                documentCollection.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return documentCollection;
    }

    public MobilePhone toMobilePhone(Document document){
        return new MobilePhone(document.getString("_id"), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("Camera Resolution"), document.getString("OS"), document.getInteger("RAM Size"));
    }

    public Camera toCamera(Document document){
        return new Camera(document.getString("_id"), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("Video Resolution"), document.getInteger("Image Resolution"), document.getInteger("ISO"));
    }

    public Laptop toLaptop(Document document){
        return new Laptop(document.getString("_id"), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("RAM Size"), document.getString("CPU Model"), document.getString("OS"));
    }

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

        ProductDB db = new ProductDB();

        //db.instertToDB(new Laptop("10", "Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10"));
        System.out.println(db.getDB().get(0).toString());



    }
}