package service;

import java.util.List;

import model.Client;
import model.ClientDTO;

public interface IClientService {
	
	public List<Client> getAll();
	
	public Client getById(int clientId);
	
	public void insert(int clientId, String name, String email);
	
	public void insertFromCsvFile(String filePath, String[] csvHeader);
	
	public Client modify(int clientId, int newClientId, String name, String email);
	
	public void delete(int clientId);
	
	public List<ClientDTO> getClientsOrderByReceiptQuantity();
	
}
