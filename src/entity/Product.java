package entity;

public class Product {
	
	private int productId;
	private String name;
	private float value;
	
	public Product() {
		
	};
	
	public Product(int id, String name, float value) {
		this.productId = id;
		this.name = name;
		this.value = value;
	}
	
	public int getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public float getValue() {
		return value;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Product: id = " + productId 
				+ ", name = " + name 
				+ ", value = " + value 
				+ ".";
	}
}
