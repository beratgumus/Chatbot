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
    private int weight;
    private double reviewPoint;

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
    }

    public Double getReviewPoint() {
        return reviewPoint;
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

    public void setReviewPoint(Double reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    public String toShortString() {
        return brand + " " + model;
    }

    @Override
    public int compareTo(Product productToCompare) {
        if (this.reviewPoint == productToCompare.getReviewPoint())
            return 0;
        else if (this.reviewPoint > productToCompare.getReviewPoint())
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
                .append("Review Point", reviewPoint);
    }

    @Override
    public String toString() {
        return brand + " " + model + " - Price: " + "$" + price +
                "\nDimensions: " + height + " x " + width + " x " + depth +
                "\nWeight: " + weight + "\nReview Point: " + reviewPoint + "\n";
    }
}