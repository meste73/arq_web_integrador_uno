package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import common.Constants;
import common.Queries;
import dto.ClientDTO;
import entity.Client;
import factory.ServiceFactory;
import handler.CustomSQLException;
import utils.CSVReader;

public class ClientDao{
	
	private final String CLASS_NAME = getClass().getName();
	
	public List<Client> getAll(){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		ArrayList<Client> clients = new ArrayList<>();
		try {
			Statement stmn = conn.createStatement();
			ResultSet rs = stmn.executeQuery(Queries.GET_ALL_CLIENTS);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				clients.add(new Client(id, name, email));
			}
			stmn.close();
		} catch(SQLException e) {
			CustomSQLException.handle(e, "Error: getAll() in Dao: " + CLASS_NAME);
		}
		return clients;
	}

	public Client getById(int id){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		Client client = new Client();
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.GET_CLIENT);
			stmn.setInt(1, id);
			ResultSet rs = DaoHelper.executePrepStatementReturnsResultSet(stmn);
			while(rs.next()) {
				client.setClientId(rs.getInt(1));
				client.setName(rs.getString(2));
				client.setEmail(rs.getString(3));
				stmn.close();
			}
		} catch(SQLException e) {
			CustomSQLException.handle(e, "Error: getById() in Dao: " + CLASS_NAME);
		}
		return client;
	}

	public void insert(int id, String name, String email){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.INSERT_CLIENT);
			stmn.setInt(1, id);
			stmn.setString(2, name);
			stmn.setString(3, email);			
			DaoHelper.executePreparedStatement(stmn);
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: insert() in Dao: " + CLASS_NAME);
		}
	}
	
	public void insertFromCsvFile(String filePath, String[] csvHeader){
		
		CSVReader reader = new CSVReader();
		Iterable<CSVRecord> records = reader.read(filePath, csvHeader);
		for (CSVRecord record : records) {
			int id = Integer.valueOf(record.get(csvHeader[0]));
			String name = record.get(csvHeader[1]);
			String email = record.get(csvHeader[2]);
			this.insert(id, name, email);
		}				
	}

	public Client modify(int oldId, int newId, String name, String email){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		Client client = new Client();
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.MODIFY_CLIENT);
			stmn.setInt(1, newId);
			stmn.setString(2, name);
			stmn.setString(3, email);
			stmn.setInt(4, oldId);			
			ResultSet rs = DaoHelper.executePrepStatementReturnsResultSet(stmn);
			if(rs != null) {
				while(rs.next()) {
					client.setClientId(rs.getInt(1));
					client.setName(rs.getString(2));
					client.setEmail(rs.getString(3));
				}				
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: modify() in Dao: " + CLASS_NAME);
		}
		return client;
	}

	public void deleteById(int id){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.DELETE_CLIENT);
			stmn.setInt(1, id);
			DaoHelper.executePreparedStatement(stmn);
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: delete() in Dao: " + CLASS_NAME);
		}
	}
	
	public List<ClientDTO> getClientsOrderByReceiptQuantity(){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		List<ClientDTO> result = new ArrayList<>();
		try {
			Statement stmn = conn.createStatement();
			ResultSet rs = stmn.executeQuery(Queries.GET_CLIENTS_ORDER_BY_RECEIPT_QUANTITY_DESC);
			if(rs != null) {
				while(rs.next()) {
					result.add(new ClientDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
				}
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getClientsOrderByReceiptQuantity() in Dao: " + CLASS_NAME);
		}
		return result;
	}
}
