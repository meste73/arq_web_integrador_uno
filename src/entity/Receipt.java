package entity;

public class Receipt {
	
	private int receiptId;
	private int clientId;
	
	public Receipt() {
		
	}
	
	public Receipt(int receiptId, int clientId) {
		this.receiptId = receiptId;
		this.clientId = clientId;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "Receipt: receiptId = " + receiptId 
				+ ", clientId = " + clientId 
				+ ".";
	}
}
