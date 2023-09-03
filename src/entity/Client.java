package entity;

public class Client {
	
	private int clientId;
	private String name;
	private String email;
	
	public Client() {
		
	}
	
	public Client(int id, String name, String email) {
		this.clientId = id;
		this.name = name;
		this.email = email;
	}
	
	public int getClientId() {
		return clientId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Client: id = " + this.clientId 
				+ ", name = " + this.name 
				+ ", email = " + this.email 
				+ ".";
	}
}
