public abstract class Product {
    private int id;
    private String brand;
    private String model;
    private Double price;
    private String category;
    private String type;
    private Double height;
    private Double width;
    private Double depth;
    private int weight;

    public Product(int id, String brand, String model, Double price, String category, String type, Double height, Double width, Double depth, int weight) {
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
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
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

    public void setType(String type){
        this.type = type;
    }

    public Double getHeight(){
        return height;
    }

    public void setHeight(Double height){
        this.height = height;
    }

    public Double getWidth(){
        return width;
    }

    public void setWidth(Double width){
        this.width = width;
    }

    public Double getDepth(){
        return depth;
    }

    public void setDepth(Double depth){
        this.depth = depth;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public  String toShortString(){
        return brand + model;
    }

    @Override
    public String toString() {
        return "Product [type=" + type + "id=" + id + ", brand=" + brand + ", model=" + model + ", price" + price + "$" + ", size=" + height + "hx" + width + "wx" + depth + "d" + ", weight=" + weight + " ]";
    }
}