package main;

import java.util.List;

import common.Constants;
import dto.ClientDTO;
import entity.Client;
import factory.DaoFactory;

public class ClientTest {
	
	private DaoFactory daoFactory;
	
	public ClientTest() {
		this.daoFactory = DaoFactory.getDaoFactory("postgres");
	}
	
	public void printAllClients() {
		List<Client> clients;
		clients = this.daoFactory.getClientDao().getAll();
		for(Client c: clients) {
			System.out.println(c);
		}
	}
	
	public void printClient(int id) {
		System.out.println(this.daoFactory.getClientDao().getById(50));
	}
	
	public void insertClientsFromCsvFile(String path, String[] headers) {
		this.daoFactory.getClientDao().insertFromCsvFile(path, headers);
	}
	
	public void insertClient(int id, String name, String email) {
		this.daoFactory.getClientDao().insert(id, name, email);
	}
	
	public Client modifyClient(int id, int newId, String name, String email) {
		return this.daoFactory.getClientDao().modify(id, newId, name, email);
	}
	
	public void deleteClient(int id) {
		this.daoFactory.getClientDao().deleteById(id);
	}
	
	public void printClientsOrderByReceiptQuantity() {
		System.out.println("Clientes ordenados descendentemente segun monto facturado:\n");
		List<ClientDTO> rClient = this.daoFactory.getClientDao().getClientsOrderByReceiptQuantity();
		if(rClient != null) {
			for(ClientDTO r: rClient) {
				System.out.println(r);
			}
		}
	}
	
	public void run() {
		insertClientsFromCsvFile(Constants.CLIENT_CSV_PATH, Constants.CLIENT_HEADERS); 
		//printAllClients();                                                             
		//modifyClient(50, 50, "Ezequiel Mestelan", "dev.mestelan@gmail.com");           
		//printClient(50);                                                               
		//deleteClient(50);                                                              
		//insertClient(50, "Marcos Mestelan", "mmestelan@gmail.com");                    
		//printClient(50);
		//printClientsOrderByReceiptQuantity();
	}
}
