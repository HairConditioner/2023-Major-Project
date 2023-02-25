package database.payload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PayloadConnection {
	
	private static PayloadConnection instance = new PayloadConnection();
	
	public static final String DRIVER_CLASS = "org.sqlite.JDBC";
	public static final String URL = "jdbc:sqlite:assets/bug.db";
	
	private PayloadConnection() {
		
		try {
			
			Class.forName(DRIVER_CLASS);
			
		}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	private Connection connectionCreate() {
		
		Connection connection = null;
		
		try {
			
			connection = DriverManager.getConnection(URL);
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			System.err.println("ERROR: Unable to connect to databases");
			
		}
		
		return connection;
		
	}
	
	public static Connection getConnection() {
		
		return instance.connectionCreate();
		
	}

}
