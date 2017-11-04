package products;

import apis.Tweet;
import apis.TwitterAPI;
import org.bson.Document;
import twitter4j.TwitterException;

import java.util.List;

public abstract class Product {
    private String id;
    private String brand;
    private String model;
    private Double price;
    private String category;
    private String type;
    private Double height;
    private Double width;
    private Double depth;
    private int weight;
    private double reviewPoint;
    List<Tweet> tweets;
    TwitterAPI twitterAPI;

    public Product(String id, String brand, String model, Double price, String category, String type, Double height, Double width, Double depth, int weight, Double reviewPoint) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.category = category;
        this.type = type;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.weight = weight;
        this.reviewPoint = reviewPoint;
        tweets = null;
        twitterAPI = new TwitterAPI();
/*
        try {
            tweets = twitterAPI.getTweets(model);
        } catch (TwitterException e) {

        }

        */
        /**
         * Todo: need to insert tweets to redis !!!!
         */

        /*

        this.reviewPoint = twitterAPI.getReviewPoint();
        System.out.println("10 tweetin Hesaplananortalamasi: " + this.reviewPoint);

        */
    }

    public Product(String brand, String model, Double price, String category, String type, Double height, Double width, Double depth, int weight) {
        this.id = null;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.category = category;
        this.type = type;
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.weight = weight;
        tweets = null;
        twitterAPI = new TwitterAPI();
        /*
        try {
            tweets = twitterAPI.getTweets(model);
        } catch (TwitterException e) {

        }

        */
        /**
         * Todo: need to insert tweets to redis !!!!
         */
/*
        this.reviewPoint = twitterAPI.getReviewPoint();
        System.out.println("10 tweetin Hesaplananortalamasi: " + this.reviewPoint);

        */
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(int pId) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String pBrand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String pModel) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double pPrice) {
        this.price = pPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Double getReviewPoint(){
        return reviewPoint;
    }

    public void setReviewPoint(Double reviewPoint){
        this.reviewPoint = reviewPoint;
    }

    public String toShortString() {
        return brand + model;
    }

    public Document toDocument() {
        return new Document("Brand", getBrand())
                .append("Model", getModel())
                .append("Price", getPrice())
                .append("Height", getHeight())
                .append("Width", getWidth())
                .append("Depth", getDepth())
                .append("Weight", getWeight())
                .append("Review Point", getReviewPoint());
    }

    @Override
    public String toString() {
        return "Product [type=" + type + "id=" + id + ", brand=" + brand + ", model=" + model + ", price" + price + "$" + ", size=" + height + "hx" + width + "wx" + depth + "d" + ", weight=" + weight + "Review Point:"  + reviewPoint + " ]";
    }
}