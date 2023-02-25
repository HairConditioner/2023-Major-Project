package database.payload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;

public class PayloadAuthentication {
		
	private static Connection connection;
	
	public static Boolean login = null;
	
	public static int authenticationVerify(String username, String pwd, String role) {
		
		login = false;
		
		try {
			
			String defaultSettings = "SELECT * FROM bug_tracking_user WHERE username = ? AND password = ? and role = ? LIMIT 1";
			
			connection = PayloadConnection.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(defaultSettings);
			
			statement.setString(1, username);
			statement.setString(2, pwd);
			statement.setString(3, role);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				int userID = resultSet.getInt("id");
				
				login = true;
				
				return userID;
				
			}
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			System.err.println("Exception in 'get()' method");
			
		} finally {
			
			Database.databaseClose(connection);
			
		}
		
		return 0;
		
	}

}
