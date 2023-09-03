package main;

import java.util.List;

import common.Constants;
import dto.ClientDTO;
import entity.Client;
import factory.ServiceFactory;

public class ClientTest {
	
	private ServiceFactory serviceFactory;
	
	public ClientTest() {
		this.serviceFactory = ServiceFactory.getServiceFactory("postgres");
	}
	
	public void printAllClients() {
		List<Client> clients;
		clients = this.serviceFactory.getClientDao().getAll();
		for(Client c: clients) {
			System.out.println(c);
		}
	}
	
	public void printClient(int id) {
		System.out.println(this.serviceFactory.getClientDao().getById(50));
	}
	
	public void insertClientsFromCsvFile(String path, String[] headers) {
		this.serviceFactory.getClientDao().insertFromCsvFile(path, headers);
	}
	
	public void insertClient(int id, String name, String email) {
		this.serviceFactory.getClientDao().insert(id, name, email);
	}
	
	public Client modifyClient(int id, int newId, String name, String email) {
		return this.serviceFactory.getClientDao().modify(id, newId, name, email);
	}
	
	public void deleteClient(int id) {
		this.serviceFactory.getClientDao().deleteById(id);
	}
	
	public void printClientsOrderByReceiptQuantity() {
		System.out.println("Clientes ordenados segun cantidad de facturas:\n");
		List<ClientDTO> rClient = this.serviceFactory.getClientDao().getClientsOrderByReceiptQuantity();
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
