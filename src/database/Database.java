package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	public static void databaseClose(Connection connection) {
		
		if (connection != null) {
			
			try {
				connection.close();
				connection = null;
			}
			catch (SQLException ignored) {}
			
		}
		
	}
	
	public static void databaseClose(Statement statement) {
		
		if (statement != null) {
			
			try {
				statement.close();
				statement = null;
			}
			catch (SQLException ignored) {}
			
		}
		
	}
	
	public static void databaseClose(ResultSet resultSet) {
		
		if (resultSet != null) {
			
			try {
				resultSet.close();
				resultSet = null;
			}
			catch (SQLException ignored) {}
			
		}
		
	}

}
