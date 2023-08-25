package model;

public class ClientDTO extends Client {
	
	private int receiptQuantity;
	
	public ClientDTO(int id, String name, String email, int receiptQuantity) {
		super(id, name, email);
		this.receiptQuantity = receiptQuantity;
	}
	
	public String toString() {
		return super.toString() + ". ReceiptQuantity: " + this.receiptQuantity;
	}
}
