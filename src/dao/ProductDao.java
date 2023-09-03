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
import dto.ProductDTO;
import entity.Product;
import factory.DaoFactory;
import handler.CustomSQLException;
import utils.CSVReader;

public class ProductDao{
	
	private final String CLASS_NAME = getClass().getName();
	
	public List<Product> getAll() {
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		ArrayList<Product> products = new ArrayList<>();
		try {
			Statement stmn = conn.createStatement();
			ResultSet rs = stmn.executeQuery(Queries.GET_ALL_PRODUCTS);
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float value = rs.getFloat(3);
				products.add(new Product(id, name, value));
			}
			stmn.close();
		} catch(SQLException e) {
			CustomSQLException.handle(e, "Error: getAll() in Dao: " + CLASS_NAME);
		}
		return products;
	}

	public Product getById(int id){
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		Product product = new Product();
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.GET_PRODUCT);
			stmn.setInt(1, id);
			ResultSet rs = DaoHelper.executePrepStatementReturnsResultSet(stmn);
			while(rs.next()) {
				product.setProductId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setValue(rs.getFloat(3));
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getById() in Dao: " + CLASS_NAME);
		}
		return product;
	}

	public void insert(int id, String name, float value){
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.INSERT_PRODUCT);
			stmn.setInt(1, id);
			stmn.setString(2, name);
			stmn.setFloat(3, value);
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
			int id = Integer.valueOf(record.get(csvHeader[0]));
			String name = record.get(csvHeader[1]);
			float value = Float.valueOf(record.get(csvHeader[2]));
			this.insert(id, name, value);
		}				
	}

	public Product modify(int oldId, int newId, String name, float value){
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		Product product = new Product();
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.MODIFY_PRODUCT);
			stmn.setInt(1, newId);
			stmn.setString(2, name);
			stmn.setFloat(3, value);
			stmn.setInt(4, oldId);
			ResultSet rs = DaoHelper.executePrepStatementReturnsResultSet(stmn);
			if(rs != null) {				
				while(rs.next()) {
					product.setProductId(rs.getInt(1));
					product.setName(rs.getString(2));
					product.setValue(rs.getFloat(3));
				}
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: modify() in Dao: " + CLASS_NAME);
		}
		return product;
	}

	public void deleteById(int id){
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		try {
			PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.DELETE_PRODUCT);
			stmn.setInt(1, id);
			DaoHelper.executePreparedStatement(stmn);
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: delete() in Dao: " + CLASS_NAME);
		}
	}
	
	public ProductDTO getHigherMoneyCollectedProduct(){
		
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		ProductDTO product = new ProductDTO();
		try {
			Statement stmn = conn.createStatement();
			ResultSet rs = stmn.executeQuery(Queries.GET_HIGHER_MONEY_COLLECTED_PRODUCT);
			if(rs != null) {
				while(rs.next()) {
					product.setId(rs.getInt(1));
					product.setName(rs.getString(2));
					product.setQuantity(rs.getInt(3));
					product.setValue(rs.getInt(4));
					product.setTotalValue(rs.getInt(5));			
				}
			}
			stmn.close();
		} catch (SQLException e) {
			CustomSQLException.handle(e, "Error: getHigherMoneyCollectedProduct() in Dao: " + CLASS_NAME);
		}
		return product;
	}

}
