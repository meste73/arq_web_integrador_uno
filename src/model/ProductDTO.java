package model;

public class ProductDTO {
	//rec.product_id, rec.name, SUM(rec.quantity) AS "Cantidad", pro.value "Valor"	//, (SUM(rec.quantity) * pro.value) AS "Valor total"
	
	private int id;
	private String name;
	private int quantity;
	private int value;
	private int totalValue;
	
	public ProductDTO() {
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getValue() {
		return value;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return "ProductDTO: id = " + id 
				+ ", name = " + name 
				+ ", quantity = " + quantity 
				+ ", value = " + value
				+ ", totalValue = " + totalValue + ".";
	}
}
