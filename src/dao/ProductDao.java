package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Queries;

public class ProductDao extends Dao {
	
	public ProductDao(Connection conn) {
		super(conn);
	}

	public ResultSet getById(int id) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.GET_PRODUCT);
		stmn.setInt(1, id);
		return DaoHelper.executePrepStatementReturnsResultSet(stmn);
	}

	public void insert(int id, String name, float value) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.INSERT_PRODUCT);
		stmn.setInt(1, id);
		stmn.setString(2, name);
		stmn.setFloat(3, value);
		DaoHelper.executePreparedStatement(stmn);
	}

	public ResultSet modify(int oldId, int newId, String name, float value) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.MODIFY_PRODUCT);
		stmn.setInt(1, newId);
		stmn.setString(2, name);
		stmn.setFloat(3, value);
		stmn.setInt(4, oldId);
		return DaoHelper.executePrepStatementReturnsResultSet(stmn);
	}

	public void deleteById(int id) throws SQLException {
		
		PreparedStatement stmn = DaoHelper.getPreparedStatement(this.conn, Queries.DELETE_PRODUCT);
		stmn.setInt(1, id);
		DaoHelper.executePreparedStatement(stmn);
	}
	
	public ResultSet getHigherMoneyCollectedProduct() throws SQLException {
		ResultSet rs = this.conn.createStatement().executeQuery(Queries.GET_HIGHER_MONEY_COLLECTED_PRODUCT);
		if(rs != null) {
			return rs;
		} else {
			throw new SQLException();
		}
	}

}
