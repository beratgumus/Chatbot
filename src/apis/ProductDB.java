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
    private MongoCollection productCollection;

    public ProductDB(){
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase("Chatbot");

        // if collection does not exist MongoDB will create one
        // see: https://docs.mongodb.com/manual/reference/method/db.getCollection/#behavior
        productCollection = db.getCollection("Products");
    }

    /**
     * Inserts new product to MongoDB. Product can be any of implemented classes (Phone, Laptop, Car ...).
     * @param product product to be inserted
     */
    public void insert(Product product){
        productCollection.insertOne(product.toDocument());
    }

    /**
     * Retrieves all products from MongoDB as a list of products.
     * @return list of products
     */
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();

        Bson sortCriteria = Sorts.descending("Review Point", "Model");

        MongoCursor<Document> cursor = productCollection.find().sort(sortCriteria).iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                String type = document.get("Type").toString();
                if (type.equals("Mobile Phone")){
                    products.add(toMobilePhone(document));
                } else if (type.equals("Laptop")){
                    products.add(toLaptop(document));
                } else if (type.equals("Refrigerator")){
                    products.add(toRefrigerator(document));
                } else if (type.equals("Car")){
                    products.add(toCar(document));
                } else if (type.equals("Motorcycle")){
                    products.add(toMotorcycle(document));
                }
            }
        } finally {
            cursor.close();
        }
        return products;
    }

    /**
     * Closes database connection
     * NOTE: Always use this function after you are done with MongoDB
     */
    public void close(){
        mongoClient.close();
    }

    private MobilePhone toMobilePhone(Document document){
        return new MobilePhone(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getDouble("Weight"), document.getDouble("Review Point"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("Camera Resolution"), document.getString("OS"), document.getInteger("RAM Size"));
    }

    private Laptop toLaptop(Document document){
        return new Laptop(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getDouble("Weight"), document.getDouble("Review Point"), document.getDouble("Screen Size"), document.getInteger("Storage Size"), document.getInteger("RAM Size"), document.getString("CPU Model"), document.getString("OS"));
    }

    private Car toCar(Document document){
        return new Car(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getDouble("Weight"), document.getDouble("Review Point"), document.getInteger("Power"), document.getString("Fuel Type"), document.getInteger("Number of Seats"), document.getString("Air Conditioner Type"));
    }

    private Motorcycle toMotorcycle(Document document){
        return new Motorcycle(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getDouble("Weight"), document.getDouble("Review Point"), document.getInteger("Power"), document.getString("Fuel Type"), document.getBoolean(""), document.getBoolean(""));
    }

    private Refrigerator toRefrigerator(Document document){
        return new Refrigerator(document.getObjectId("_id").toString(), document.getString("Brand"), document.getString("Model"), document.getDouble("Price"), document.getDouble("Height"), document.getDouble("Width"), document.getDouble("Depth"), document.getDouble("Weight"), document.getDouble("Review Point"), document.getInteger("Capacity"), document.getString("Energy Efficiency"), document.getString("Refrigerator Type"), document.getBoolean("Ice Maker"), document.getBoolean("Frost Free"), document.getBoolean("Door Open Alarm"));
    }
}