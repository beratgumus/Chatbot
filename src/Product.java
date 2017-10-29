public abstract class Product {
    private int id;
    private String brand;
    private String model;
    private Double price;
    private String machineType;

    public Product(int id, String brand, String model, Double price, String machineType) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.machineType = machineType;
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

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType){
        this.machineType = machineType;
    }


    @Override
    public String toString() {
        return "Product [machineType=" + machineType + "id=" + id + ", brand=" + brand + ", model=" + model + ", price" + price + "$" + "]";
    }
}