package apis;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import products.*;
import com.mongodb.client.model.Sorts;

public class ProductDB {

    private MongoClient mongoClient;
    private MongoDatabase db;
    private MongoCollection testCollection;

    public ProductDB(){
        mongoClient = new MongoClient();

        db = mongoClient.getDatabase("Chatbot");

        // if collection does not exsist mongo will create one
        // see: https://docs.mongodb.com/manual/reference/method/db.getCollection/#behavior
        testCollection = db.getCollection("Products");
    }
    //test method1
    public void insert(Product product){
        testCollection.insertOne(product.toDocument());
    }

    //test method2
    public List<Product> retrieveAll(){
        List<Document> documentCollection = new ArrayList<>();

        Bson sortCriteria = Sorts.descending("Review Point", "Model");

        MongoCursor<Document> cursor = testCollection.find().sort(sortCriteria).iterator();
        try {
            while (cursor.hasNext()) {
                documentCollection.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        List<Product> products = new ArrayList<>();
        for (Document document : documentCollection){
            String type = document.get("Type").toString();
            if (type.equals("Mobile Phone")){
                products.add(toMobilePhone(document));
            } else if (type.equals("Laptop")){
                products.add(toLaptop(document));
            }
        }
        return products;
    }

    /**
     * Closes database connection
     */
    public void close(){
        mongoClient.close();
    }

    public MobilePhone toMobilePhone(Document document){
        return new MobilePhone(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("Camera Resolution"), document.getString("OS"), document.getInteger("RAM Size"));
    }

    public Camera toCamera(Document document){
        return new Camera(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("Video Resolution"), document.getInteger("Image Resolution"), document.getInteger("ISO"));
    }

    public Laptop toLaptop(Document document){
        return new Laptop(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("RAM Size"), document.getString("CPU Model"), document.getString("OS"));
    }

    public Car toCar(Document document){
        return new Car(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getString("Vehicle Type"), document.getInteger("Power"), document.getString("Fuel Type"), document.getInteger("Number of Seats"), document.getString("Air Conditioner Type"));
    }

    public Motorcycle toMotorcycle(Document document){
        return new Motorcycle(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getString("Vehicle Type"), document.getInteger("Power"), document.getString("Fuel Type"), document.getBoolean(""), document.getBoolean(""));
    }

    public Refrigerator toRefrigerator(Document document){
        return new Refrigerator(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getInteger("Capacity"), document.getString("Energy Efficiency"), document.getString("Refrigerator Type"), document.getBoolean("Ice Maker"), document.getBoolean("Frost Free"), document.getBoolean("Door Open Alarm"));
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

        /*
        ProductDB db = new ProductDB();

        Laptop a = new Laptop("Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10");
        Laptop b = new Laptop("Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10");
        Laptop c = new Laptop("Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10");
        Laptop d = new Laptop("Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10");
        Laptop e = new Laptop("Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10");
        Laptop f = new Laptop("Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10");
        Laptop g = new Laptop("Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10");
        Laptop h = new Laptop("Lenovo", "G5080A", 1450.0, 26.0, 51.0, 8.0, 2560, 15.6, 500, 4, "i3 4030u", "Windows 10");

        a.setReviewPoint(0.55);
        b.setReviewPoint(-2.51);
        c.setReviewPoint(3.46);
        d.setReviewPoint(0.545);
        e.setReviewPoint(6.54);
        f.setReviewPoint(-5.4);
        g.setReviewPoint(0.1);
        h.setReviewPoint(1.3);

        db.insertToDB(a);
        db.insertToDB(b);
        db.insertToDB(c);
        db.insertToDB(d);
        db.insertToDB(e);
        db.insertToDB(f);
        db.insertToDB(g);
        db.insertToDB(h);

        System.out.println(db.getDB().get(0).toString());
        System.out.println(db.getDB().get(1).toString());
        System.out.println(db.getDB().get(2).toString());
        System.out.println(db.getDB().get(3).toString());
        System.out.println(db.getDB().get(4).toString());
        System.out.println(db.getDB().get(5).toString());
        System.out.println(db.getDB().get(6).toString());
        System.out.println(db.getDB().get(7).toString());

        */
    }
}