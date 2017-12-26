package products;

import org.bson.Document;

public abstract class Product implements Comparable<Product> {
    private String id;
    private String brand;
    private String model;
    private Double price;
    private String category;
    private String type;
    private Double height;
    private Double width;
    private Double depth;
    private Double weight;
    private double reviewPoint1;
    private double reviewPoint2;

    public Product(String id, String brand, String model, Double price, String category, String type, Double height, Double width, Double depth, Double weight, Double reviewPoint1,Double reviewPoint2) {
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
        this.reviewPoint1 = reviewPoint1;
        this.reviewPoint2 = reviewPoint2;

    }

    public Product(String brand, String model, Double price, String category, String type, Double height, Double width, Double depth, Double weight) {
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
    }

    public Double getReviewPoint1() {
        return reviewPoint1;
    }

    public Double getReviewPoint2() {
        return reviewPoint2;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public void setReviewPoint1(Double reviewPoint1) {
        this.reviewPoint1 = reviewPoint1;
    }

    public void setReviewPoint2(Double reviewPoint2) {
        this.reviewPoint2 = reviewPoint2;
    }

    public String toShortString() {
        return brand + " " + model;
    }

    @Override
    public int compareTo(Product productToCompare) {
        if (this.reviewPoint1 == productToCompare.getReviewPoint1())
            return 0;
        else if (this.reviewPoint1 > productToCompare.getReviewPoint1())
            return -1;
        else
            return 1;
    }

    public Document toDocument() {
        return new Document("Brand", brand)
                .append("Model", model)
                .append("Price", price)
                .append("Type", type)
                .append("Height", height)
                .append("Width", width)
                .append("Depth", depth)
                .append("Weight", weight)
                .append("Review Point1", reviewPoint1)
                .append("Review Point2", reviewPoint2);
    }

    @Override
    public String toString() {
        return brand + " " + model + " - Price: " + "$" + price +
                "\nDimensions: " + height + " x " + width + " x " + depth +
                "\nWeight: " + weight + "\nReview Point1: " + reviewPoint1 + "\nReview Point2: " + reviewPoint2 + "\n";
    }
}