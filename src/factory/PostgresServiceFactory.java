package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.Constants;
import dao.ClientDao;
import dao.ProductDao;
import dao.ReceiptDao;
import dao.ReceiptProductDao;
import handler.CustomSQLException;
import service.ClientService;
import service.IClientService;
import service.IProductService;
import service.IReceiptProductService;
import service.IReceiptService;
import service.ProductService;
import service.ReceiptProductService;
import service.ReceiptService;

public class PostgresServiceFactory extends ServiceFactory{
	
	private static Connection conn;
	private IClientService clientService;
	private IProductService productService;
	private IReceiptService receiptService;
	private IReceiptProductService receiptProductService;
	
	protected PostgresServiceFactory() {
		this.createConnection();
		this.clientService = new ClientService(new ClientDao(conn));
		this.productService = new ProductService(new ProductDao(conn));
		this.receiptService = new ReceiptService(new ReceiptDao(conn));
		this.receiptProductService = new ReceiptProductService(new ReceiptProductDao(conn));
	}

	@Override
	public IClientService getClientService() {
		return this.clientService;
	}

	@Override
	public IProductService getProductService() {
		return this.productService;
	}

	@Override
	public IReceiptService getReceiptService() {
		return this.receiptService;
	}

	@Override
	public IReceiptProductService getReceiptProductService() {
		return this.receiptProductService;
	}
	
	public void createTable(String query) {
		
		try {
			PreparedStatement stmn = conn.prepareStatement(query);
			if(!stmn.execute()) {
				System.out.println("Create table query succesfully ran");
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "createTable()");
		}
	}
	
	private Connection createConnection(){
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if(conn != null) {
			try {
				if(!conn.isClosed()) {
					conn.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
