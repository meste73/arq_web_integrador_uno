package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao{
	
	protected Connection conn;
	
	public Dao(Connection conn) {
		this.conn = conn;
	}
	
	public ResultSet getAll(String query) throws SQLException {
		ResultSet rs = this.conn.createStatement().executeQuery(query);
		if(rs != null) {
			return rs;
		} else {
			throw new SQLException();
		}
	}
}
