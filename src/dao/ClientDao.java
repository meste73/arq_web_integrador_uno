package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Queries;

public class ClientDao extends Dao {
	
	public ClientDao(Connection conn) {
		super(conn);
	}

	public ResultSet getById(int id) throws SQLException{
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.GET_CLIENT);
		stmn.setInt(1, id);
		return DaoHelper.executePrepStatementReturnsResultSet(stmn);
	}

	public void insert(int id, String name, String email) throws SQLException{
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.INSERT_CLIENT);
		stmn.setInt(1, id);
		stmn.setString(2, name);
		stmn.setString(3, email);			
		DaoHelper.executePreparedStatement(stmn);
	}

	public ResultSet modify(int oldId, int newId, String name, String email) throws SQLException{
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.MODIFY_CLIENT);
		stmn.setInt(1, newId);
		stmn.setString(2, name);
		stmn.setString(3, email);
		stmn.setInt(4, oldId);			
		return DaoHelper.executePrepStatementReturnsResultSet(stmn);
	}

	public void deleteById(int id) throws SQLException{
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.DELETE_CLIENT);
		stmn.setInt(1, id);
		DaoHelper.executePreparedStatement(stmn);
	}
	
	public ResultSet getClientsOrderByReceiptQuantity() throws SQLException {
		ResultSet rs = this.conn.createStatement().executeQuery(Queries.GET_CLIENTS_ORDER_BY_RECEIPT_QUANTITY_DESC);
		if(rs != null) {
			return rs;
		} else {
			throw new SQLException();
		}
	}
}
