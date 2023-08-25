package handler;

import java.sql.SQLException;

public class CustomSQLException extends SQLException{

	private static final long serialVersionUID = 1L;
	
	public static void handle(SQLException e, String message) {
		StringBuilder exceptionMessage = new StringBuilder(message + ".\n");
		exceptionMessage.append("Error message: " + e.getMessage());
		exceptionMessage.append("Error code: " + e.getErrorCode() + ".\n");
		exceptionMessage.append("Cause: " + e.getCause() + ".");
		System.out.println(exceptionMessage.toString());
	}
}
