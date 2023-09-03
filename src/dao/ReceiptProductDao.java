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
import entity.ReceiptProduct;
import factory.ServiceFactory;
import handler.CustomSQLException;
import utils.CSVReader;

public class ReceiptProductDao{
	
	private String CLASS_NAME = getClass().getName();
	
	public List<ReceiptProduct> getAll() {
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		List<ReceiptProduct> receiptProducts = new ArrayList<>();
		try {
			Statement stmn = conn.createStatement();
			ResultSet rs = stmn.executeQuery(Queries.GET_ALL_RECEIPT_PRODUCTS);
			if(rs != null) {
				while(rs.next()) {
					int receiptId = rs.getInt(1);
					int productId = rs.getInt(2);
					int quantity = rs.getInt(3);
					receiptProducts.add(new ReceiptProduct(receiptId, productId, quantity));
				}
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getAll() in Dao: " + CLASS_NAME);
		}
		return receiptProducts;
	}

	public ReceiptProduct getById(int receiptId, int productId){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		ReceiptProduct receiptProduct = new ReceiptProduct();
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.GET_RECEIPT_PRODUCT);
			stmn.setInt(1, receiptId);
			stmn.setInt(2, productId);
			ResultSet rs = DaoHelper.executePrepStatementReturnsResultSet(stmn);
			if(rs != null) {
				while(rs.next()) {
					receiptProduct.setReceiptId(rs.getInt(1));
					receiptProduct.setProductId(rs.getInt(2));
					receiptProduct.setQuantity(rs.getInt(3));
				}
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getById() in Dao: " + CLASS_NAME);
		}
		return receiptProduct;
	}

	public void insert(int receiptId, int productId, int quantity){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.INSERT_RECEIPT_PRODUCT);
			stmn.setInt(1, receiptId);
			stmn.setInt(2, productId);
			stmn.setInt(3, quantity);
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
			int productId = Integer.valueOf(record.get(csvHeader[1]));
			int quantity = Integer.valueOf(record.get(csvHeader[2]));
			this.insert(receiptId, productId, quantity);
		}				
	}

	public ReceiptProduct modify(int oldReceiptId, 
							int newReceiptId, 
							int oldProductId, 
							int newProductId, 
							int quantity){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		ReceiptProduct receiptProduct = new ReceiptProduct();
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.MODIFY_RECEIPT_PRODUCT);
			stmn.setInt(1, newReceiptId);
			stmn.setInt(2, newProductId);
			stmn.setInt(3, quantity);
			stmn.setInt(4, oldReceiptId);
			stmn.setInt(5, oldProductId);
			ResultSet rs = DaoHelper.executePrepStatementReturnsResultSet(stmn);
			if(rs != null) {
				while(rs.next()) {
					receiptProduct.setReceiptId(rs.getInt(1));
					receiptProduct.setProductId(rs.getInt(2));
					receiptProduct.setQuantity(rs.getInt(3));
				}
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: 'modify() in Dao: " + CLASS_NAME);
		}
		return receiptProduct;
	}

	public void deleteById(int receiptId, int productId){
		
		Connection conn = ServiceFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.DELETE_RECEIPT_PRODUCT);
			stmn.setInt(1, receiptId);
			stmn.setInt(2, productId);
			DaoHelper.executePreparedStatement(stmn);
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: delete() in Dao: " + CLASS_NAME);
		}
		
		
	}
}
