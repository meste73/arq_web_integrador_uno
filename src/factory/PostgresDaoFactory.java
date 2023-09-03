package factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.Constants;
import dao.ClientDao;
import dao.ProductDao;
import dao.ReceiptDao;
import dao.ReceiptProductDao;
import handler.CustomSQLException;

public class PostgresDaoFactory extends DaoFactory{
	
	private ClientDao clientDao;
	private ProductDao productDao;
	private ReceiptDao receiptDao;
	private ReceiptProductDao receiptProductDao;
	
	protected PostgresDaoFactory() {
		this.clientDao = new ClientDao();
		this.productDao = new ProductDao();
		this.receiptDao = new ReceiptDao();
		this.receiptProductDao = new ReceiptProductDao();
	}

	@Override
	public ClientDao getClientDao() {
		return this.clientDao;
	}

	@Override
	public ProductDao getProductDao() {
		return this.productDao;
	}

	@Override
	public ReceiptDao getReceiptDao() {
		return this.receiptDao;
	}

	@Override
	public ReceiptProductDao getReceiptProductDao() {
		return this.receiptProductDao;
	}
	
	public void createTable(String query) {
		Connection conn = DaoFactory.getConnection(Constants.POSTGRES_URL, Constants.POSTGRES_USERNAME, Constants.POSTGRES_PASSWORD);
		try {
			PreparedStatement stmn = conn.prepareStatement(query);
			if(!stmn.execute()) {
				System.out.println("Create table query succesfully ran");
			}
		} catch (SQLException e) {
			CustomSQLException.handle(e, "createTable()");
		}
	}
}
