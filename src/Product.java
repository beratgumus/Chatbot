public abstract class Product {
	private int id;
	private String brand;
	private String model;
	private String machineType;
	private Double price;

	public Product(int id, String brand, String model, String machineType, Double price) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.machineType = machineType;
		this.price = price;
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

	public String getTypel() {
		return machineType;
	}

	public void setType(String pModel) {
		this.machineType = machineType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double pPrice) {
		this.price = pPrice;
	}

	@Override
	public String toString() {
		return "Product [pId=" + id + ", pBrand=" + brand + ", pModel=" + model + ", pType" + machineType + ", pPrice" + price + "]";
	}
}
