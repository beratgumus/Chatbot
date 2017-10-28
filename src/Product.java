public abstract class Product {
	private int pId;
	private String pBrand;
	private String pModel;
	private String pType;
	private Double pPrice;

	public Product(int pId, String pBrand, String pModel, String pType, Double pPrice) {
		this.pId = pId;
		this.pBrand = pBrand;
		this.pModel = pModel;
		this.pType = pType;
		this.pPrice = pPrice;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpBrand() {
		return pBrand;
	}

	public void setpBrand(String pBrand) {
		this.pBrand = pBrand;
	}

	public String getpModel() {
		return pModel;
	}

	public void setpModel(String pModel) {
		this.pModel = pModel;
	}

	public String getpTypel() {
		return pType;
	}

	public void setpType(String pModel) {
		this.pType = pType;
	}

	public Double getpPrice() {
		return pPrice;
	}

	public void setpPrice(Double pPrice) {
		this.pPrice = pPrice;
	}

	@Override
	public String toString() {
		return "Product [pId=" + pId + ", pBrand=" + pBrand + ", pModel=" + pModel + ", pType" + pType + ", pPrice" + pPrice + "]";
	}
}
