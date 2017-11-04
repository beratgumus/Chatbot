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
    private MongoDatabase products;
    private MongoCollection mobilePhones;
    private MongoCollection laptops;
    private MongoCollection cameras;
    private MongoCollection cars;
    private MongoCollection motorcycles;
    private MongoCollection refrigerators;

    public ProductDB(){
        mongoClient = new MongoClient();
        products = mongoClient.getDatabase("Products");
        mobilePhones = products.getCollection("Mobile Phones");
        laptops = products.getCollection("Laptops");
        cameras = products.getCollection("Cameras");
        cars = products.getCollection("Cars");
        motorcycles = products.getCollection("Motorcycles");
        refrigerators = products.getCollection("Refrigerators");
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

    public void insertToDB(Car car){
        cars.insertOne(car.toDocument());
    }

    public void insertToDB(Motorcycle motorcycle){
        motorcycles.insertOne(motorcycle.toDocument());
    }

    public void insertToDB(Refrigerator refrigerator){
        refrigerators.insertOne(refrigerator.toDocument());
    }

    public MobilePhone toMobilePhone(Document document){
        return new MobilePhone(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("Camera Resolution"), document.getString("OS"), document.getInteger("RAM Size"));
    }

    public void toMobilePhone(List<Document> mobilePhoneCollection, List<Product> productCollection){
        for(Document product : mobilePhoneCollection){
            productCollection.add(toMobilePhone(product));
        }
    }

    public Camera toCamera(Document document){
        return new Camera(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("Video Resolution"), document.getInteger("Image Resolution"), document.getInteger("ISO"));
    }

    public void toCamera(List<Document> cameraCollection, List<Product> productCollection){
        for(Document product : cameraCollection){
            productCollection.add(toCamera(product));
        }
    }

    public Laptop toLaptop(Document document){
        return new Laptop(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("RAM Size"), document.getString("CPU Model"), document.getString("OS"));
    }

    public void toLaptop(List<Document> laptopCollection, List<Product> productCollection){
        for(Document product : laptopCollection){
            productCollection.add(toLaptop(product));
        }
    }

    public Car toCar(Document document){
        return new Car(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getString("Vehicle Type"), document.getInteger("Power"), document.getString("Fuel Type"), document.getInteger("Number of Seats"), document.getString("Air Conditioner Type"));
    }

    public void toCar(List<Document> carCollection, List<Product> productCollection){
        for(Document product : carCollection){
            productCollection.add(toCar(product));
        }
    }

    public Motorcycle toMotorcycle(Document document){
        return new Motorcycle(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getString("Vehicle Type"), document.getInteger("Power"), document.getString("Fuel Type"), document.getBoolean(""), document.getBoolean(""));
    }

    public void toMotorcycle(List<Document> motorcycleCollection, List<Product> productCollection){
        for(Document product : motorcycleCollection){
            productCollection.add(toMotorcycle(product));
        }
    }

    public Refrigerator toRefrigerator(Document document){
        return new Refrigerator(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getInteger("Weight"), document.getDouble("Review Point"), document.getInteger("Capacity"), document.getString("Energy Efficiency"), document.getString("Refrigerator Type"), document.getBoolean("Ice Maker"), document.getBoolean("Frost Free"), document.getBoolean("Door Open Alarm"));
    }

    public void toRefrigerator(List<Document> refrigeratorCollection, List<Product> productCollection){
        for(Document product : refrigeratorCollection){
            productCollection.add(toRefrigerator(product));
        }
    }

    public List<Document> documentCollector(MongoCollection mongoCollection){
        List<Document> documentCollection = new ArrayList<>();

        Bson sortCriteria = Sorts.descending("Review Point", "Model");

        MongoCursor<Document> cursor = mongoCollection.find().sort(sortCriteria).iterator();
        try {
            while (cursor.hasNext()) {
                documentCollection.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return documentCollection;
    }

    public List<Product> getDB(){
        List<Product> productCollection = new ArrayList<>();
        List<Document> mobilePhoneCollection = documentCollector(mobilePhones);;
        List<Document> laptopCollection = documentCollector(laptops);
        List<Document> cameraCollection = documentCollector(cameras);
        List<Document> carCollection = documentCollector(cars);
        List<Document> motorcycleCollection = documentCollector(motorcycles);
        List<Document> refrigeratorCollection = documentCollector(refrigerators);

        toMobilePhone(mobilePhoneCollection, productCollection);
        toLaptop(laptopCollection, productCollection);
        toCamera(cameraCollection, productCollection);
        toCar(carCollection, productCollection);
        toMotorcycle(motorcycleCollection, productCollection);
        toRefrigerator(refrigeratorCollection, productCollection);

        return productCollection;
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

        db.instertToDB(a);
        db.instertToDB(b);
        db.instertToDB(c);
        db.instertToDB(d);
        db.instertToDB(e);
        db.instertToDB(f);
        db.instertToDB(g);
        db.instertToDB(h);

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