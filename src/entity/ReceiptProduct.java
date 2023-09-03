package entity;

public class ReceiptProduct {
	
	private int receiptId;
	private int productId;
	private int quantity;
	
	public ReceiptProduct() {
		
	}
	
	public ReceiptProduct(int receiptId, int productId, int quantity) {
		this.receiptId = receiptId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ReceiptProduct: receiptId = " + receiptId 
				+ ", productId = " + productId 
				+ ", quantity = " + quantity
				+ ".";
	}
}
