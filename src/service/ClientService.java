package service;

import common.Queries;

import dao.ClientDao;

import handler.CustomSQLException;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.ClientDTO;

import org.apache.commons.csv.CSVRecord;

import utils.CSVReader;

public class ClientService implements IClientService {
	
	private final String CLASS_NAME = getClass().getName();
	
	private ClientDao clientDao;
	
	public ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	@Override
	public List<Client> getAll(){
		
		ArrayList<Client> clients = new ArrayList<>();
		try {
			ResultSet rs = clientDao.getAll(Queries.GET_ALL_CLIENTS);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				clients.add(new Client(id, name, email));
			}
		} catch(SQLException e) {
			CustomSQLException.handle(e, "Error: getAll() in Service: " + CLASS_NAME);
		}
		return clients;
	}

	@Override
	public Client getById(int clientId) {
		
		Client client = new Client();
		try {
			ResultSet rs = clientDao.getById(clientId);
			while(rs.next()) {
				client.setClientId(rs.getInt(1));
				client.setName(rs.getString(2));
				client.setEmail(rs.getString(3));
			}			
		} catch(SQLException e) {
			CustomSQLException.handle(e, "Error: getById() in Service: " + CLASS_NAME);
		}
		return client;
	}

	@Override
	public void insert(int clientId, String name, String email) {
		try {
			this.clientDao.insert(clientId, name, email);
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: insert() in Service: " + CLASS_NAME);
		}
	}

	@Override
	public void insertFromCsvFile(String filePath, String[] csvHeader){
		
		CSVReader reader = new CSVReader();
		Iterable<CSVRecord> records = reader.read(filePath, csvHeader);
		
		if(records != null) {
			try {
				for (CSVRecord record : records) {
					int id = Integer.valueOf(record.get(csvHeader[0]));
					String name = record.get(csvHeader[1]);
					String email = record.get(csvHeader[2]);
					this.clientDao.insert(id, name, email);
				}				
			} catch(SQLException e) {
				CustomSQLException.handle(e, "Error: insertFromCsvFile() in Service: " + CLASS_NAME);
			}
		}
	}

	@Override
	public Client modify(int clientId, int newclientId, String name, String email) {
		Client client = new Client();
		try {
			ResultSet rs = this.clientDao.modify(clientId, newclientId, name, email);
			if(rs != null) {
				while(rs.next()) {
					client.setClientId(rs.getInt(1));
					client.setName(rs.getString(2));
					client.setEmail(rs.getString(3));
				}				
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: modify() in Service: " + CLASS_NAME);

		}
		return client;
	}

	@Override
	public void delete(int clientId) {
		try {
			this.clientDao.deleteById(clientId);
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: delete() in Service: " + CLASS_NAME);
		}
	}
	
	@Override
	public List<ClientDTO> getClientsOrderByReceiptQuantity() {
		List<ClientDTO> recClients = new ArrayList<>();
		try {
			ResultSet rs = this.clientDao.getClientsOrderByReceiptQuantity();
			if(rs != null) {
				while(rs.next()) {
					recClients.add(new ClientDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
				}
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getClientsOrderByReceiptQuantity() in Service: " + CLASS_NAME);
		}
		return recClients;
	}
}
