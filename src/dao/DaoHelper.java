package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import handler.CustomSQLException;

public class DaoHelper {
	
	protected static void executePreparedStatement(PreparedStatement stmn){
		try {
			stmn.execute();
		} catch (SQLException e) {
			CustomSQLException.handle(new SQLException(), "Error: executePreparedStatement()");
		}
	}
	
	protected static ResultSet executePrepStatementReturnsResultSet(PreparedStatement stmn){
		
		ResultSet rs = null;
		try {
			if(stmn.execute()) {
				rs = stmn.getResultSet();
			}
		} catch (SQLException e) {
			CustomSQLException.handle(new SQLException(), "Statement could not be returned.");
		}
		return rs;
		
	}
	
	protected static PreparedStatement getPreparedStatement(Connection conn, String query){
		
		PreparedStatement stmn = null;
		try {
			stmn = conn.prepareStatement(query);
		} catch (SQLException e) {
			CustomSQLException.handle(new SQLException(), "Statement could not be returned.");
		}
		return stmn;
	}
}
