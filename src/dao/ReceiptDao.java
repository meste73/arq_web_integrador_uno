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
import entity.Receipt;
import factory.DaoFactory;
import handler.CustomSQLException;
import utils.CSVReader;

public class ReceiptDao{
	
	private final String CLASS_NAME = getClass().getName();
	
	public List<Receipt> getAll() {
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		List<Receipt> receipts = new ArrayList<>();
		try {
			Statement stmn = conn.createStatement();
			ResultSet rs = stmn.executeQuery(Queries.GET_ALL_RECEIPTS);
			if(rs != null) {
				while(rs.next()) {
					int receiptId = rs.getInt(1);
					int clientId = rs.getInt(2);
					receipts.add(new Receipt(receiptId, clientId));
				}
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getAll() in Dao: " + CLASS_NAME);
		}
		return receipts;
	}

	public Receipt getById(int id){
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		Receipt receipt = new Receipt();
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.GET_RECEIPT);
			stmn.setInt(1, id);
			ResultSet rs = DaoHelper.executePrepStatementReturnsResultSet(stmn);
			if(rs != null) {
				while(rs.next()) {
					receipt.setReceiptId(rs.getInt(1));
					receipt.setClientId(rs.getInt(2));
				}
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getById() in Dao: " + CLASS_NAME);
		}
		return receipt;
	}

	public void insert(int receiptId, int clientId){
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.INSERT_RECEIPT);
			stmn.setInt(1, receiptId);
			stmn.setInt(2, clientId);
			DaoHelper.executePreparedStatement(stmn);
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: insert() in Dao: " + CLASS_NAME);
		}
	}
	
	public void insertFromCsvFile(String filePath, String[] csvHeader) {
		
		CSVReader reader = new CSVReader();
		Iterable<CSVRecord> records = reader.read(filePath, csvHeader);
		for (CSVRecord record : records) {
			int receiptId = Integer.valueOf(record.get(csvHeader[0]));
			int clientId = Integer.valueOf(record.get(csvHeader[1]));
			this.insert(receiptId, clientId);
		}				
	}

	public Receipt modify(int oldReceiptId, int newReceiptId, int clientId){
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		Receipt receipt = new Receipt();
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.MODIFY_RECEIPT);
			stmn.setInt(1, newReceiptId);
			stmn.setInt(2, clientId);
			stmn.setInt(3, oldReceiptId);
			ResultSet rs = DaoHelper.executePrepStatementReturnsResultSet(stmn);
			if(rs != null) {
				while(rs.next()) {
					receipt.setReceiptId(rs.getInt(1));
					receipt.setClientId(rs.getInt(2));
				}
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: modify() in Dao: " + CLASS_NAME);
		}
		return receipt;
	}

	public void deleteById(int id){
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.DELETE_RECEIPT);
			stmn.setInt(1, id);
			DaoHelper.executePreparedStatement(stmn);
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: delete() in Dao: " + CLASS_NAME);
		}	
	}
}
