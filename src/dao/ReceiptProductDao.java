package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Queries;

public class ReceiptProductDao extends Dao {
	
	public ReceiptProductDao(Connection conn) {
		super(conn);
	}

	public ResultSet getById(int receiptId, int productId) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.GET_RECEIPT_PRODUCT);
		stmn.setInt(1, receiptId);
		stmn.setInt(2, productId);
		return DaoHelper.executePrepStatementReturnsResultSet(stmn);
	}

	public void insert(int receiptId, int productId, int quantity) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.INSERT_RECEIPT_PRODUCT);
		stmn.setInt(1, receiptId);
		stmn.setInt(2, productId);
		stmn.setInt(3, quantity);
		DaoHelper.executePreparedStatement(stmn);
	}

	public ResultSet modify(int oldReceiptId, 
							int newReceiptId, 
							int oldProductId, 
							int newProductId, 
							int quantity) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.MODIFY_RECEIPT_PRODUCT);
		stmn.setInt(1, newReceiptId);
		stmn.setInt(2, newProductId);
		stmn.setInt(3, quantity);
		stmn.setInt(4, oldReceiptId);
		stmn.setInt(5, oldProductId);
		return DaoHelper.executePrepStatementReturnsResultSet(stmn);
	}

	public void deleteById(int receiptId, int productId) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(conn, Queries.DELETE_RECEIPT_PRODUCT);
		stmn.setInt(1, receiptId);
		stmn.setInt(2, productId);
		DaoHelper.executePreparedStatement(stmn);
	}
}
