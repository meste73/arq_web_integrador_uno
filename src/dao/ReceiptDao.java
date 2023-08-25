package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Queries;

public class ReceiptDao extends Dao {
	
	public ReceiptDao(Connection conn) {
		super(conn);
	}

	public ResultSet getById(int id) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.GET_RECEIPT);
		stmn.setInt(1, id);
		return DaoHelper.executePrepStatementReturnsResultSet(stmn);
	}

	public void insert(int receiptId, int clientId) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.INSERT_RECEIPT);
		stmn.setInt(1, receiptId);
		stmn.setInt(2, clientId);
		DaoHelper.executePreparedStatement(stmn);
	}

	public ResultSet modify(int oldReceiptId, int newReceiptId, int clientId) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.MODIFY_RECEIPT);
		stmn.setInt(1, newReceiptId);
		stmn.setInt(2, clientId);
		stmn.setInt(3, oldReceiptId);
		return DaoHelper.executePrepStatementReturnsResultSet(stmn);
	}

	public void deleteById(int id) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.DELETE_RECEIPT);
		stmn.setInt(1, id);
		DaoHelper.executePreparedStatement(stmn);
	}
}
