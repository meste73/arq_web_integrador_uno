package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.ClientDao;
import dao.ProductDao;
import dao.ReceiptDao;
import dao.ReceiptProductDao;

public abstract class ServiceFactory {
	
	private static PostgresServiceFactory postgresServiceFactory;
	private static Connection conn;
	
	public static ServiceFactory getServiceFactory(String dataBase) {
		if(dataBase == "postgres") {
			if(postgresServiceFactory == null) {
				postgresServiceFactory = new PostgresServiceFactory();
			}
			return postgresServiceFactory;			
		}
		return null;
	}
	
	public static Connection getConnection(String url, String username, String password){
		try {
			if(conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(url, username, password);								
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection() {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public abstract ClientDao getClientDao();
	
	public abstract ProductDao getProductDao();
	
	public abstract ReceiptDao getReceiptDao();
	
	public abstract ReceiptProductDao getReceiptProductDao();
	
	public abstract void createTable(String query);
	
}
