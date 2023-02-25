package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import database.payload.PayloadConnection;

public class ModelBug {
		
	private ResultSet results;
	private Connection connection;
	
	public ModelBug() {
		
		connection = PayloadConnection.getConnection();
		
	}
	
	public int bugAdd(String bugProjectID, String bugDeveloperID, String bugTesterID, String bugTitle, String bugDesc, String bugPriority, String bugStatus, String bugDueDate) {
		
		int bugID = 0;
		
		try {
			
			String defaultBugsSettings = "INSERT INTO bugs(bugTitle, bugDescription , bugPriority, bugStatus , bugDueDate , bugtesterID , bugdevID ,bugProjectID)" + "" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(defaultBugsSettings);
			
			statement.setString(1, bugTitle);
			statement.setString(2, bugDesc);
			statement.setString(3, bugPriority);
			statement.setString(4, bugStatus);
			statement.setString(5, bugDueDate);
			statement.setString(6, bugTesterID);
			statement.setString(7, bugDeveloperID);
			statement.setString(8, bugProjectID);
			
			statement.executeUpdate();
			
			PreparedStatement statementReturned = connection.prepareStatement(defaultBugsSettings);
			
			results = statementReturned.executeQuery("select last_insert_id()");
			
			while (results.next()) {
				
				bugID = results.getInt(1);
				
			}
			
		}
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
			
		}
		
		return bugID;
		
	}
	
	public int bugClose(int bugID) {
		
		try {
			
			String updatedBugSettings = "UPDATE bugs SET bugStatus = 'CLOSE' WHERE bugID = ?";
			
			PreparedStatement statement = connection.prepareStatement(updatedBugSettings);
			
			statement.setInt(1, bugID);
			
			statement.executeUpdate();
			
		}
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
			
		}
		
		return bugID;
		
	}
	
	public void bugEditStatus(int bugID) {
		
		try {
			
			String updatedBugSettings = "SELECT * FROM bugs WHERE bugID = ? AND bugStatus != ?";
			
			PreparedStatement statement = connection.prepareStatement(updatedBugSettings);
			
			statement.setInt(1, bugID);
			statement.setString(2, "CLOSED");
			
			statement.executeUpdate();
			
			if (results.next()) {
				
				String editedBugSettings = "UPDATE bugs SET bugStatus = 'INPROGRESS' WHERE bugID = ?";
				
				PreparedStatement editedStatement = connection.prepareStatement(editedBugSettings);
				
				editedStatement.setInt(1, bugID);
				
				editedStatement.executeUpdate();
				
			}
			else {
				
				JOptionPane.showMessageDialog(null, "This bug has already been closed");
				
			}
			
		}
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
			
		}
		
	}
	
	public int searchBug(int bugID) {
		
		try {
			
			String selectedBug = "SELECT * FROM bugs WHERE bugID = ?";
			
			PreparedStatement statement = connection.prepareStatement(selectedBug);
			
			statement.setInt(1, bugID);
			
			statement.executeUpdate();
			
			while (results.next()) {
				
				JOptionPane.showMessageDialog(null,
						"Bug Title: " + results.getString(2) +
						"\nBug Description: " + results.getString(3) +
						"\nBug Priority: " + results.getString(4) +
						"\nBug Status: " + results.getString(5) +
						"\nBug Due Date " + results.getString(6) +
						"\nBug Tester ID: " + results.getString(7) +
						"\nBug Developer ID: " + results.getString(8) +
						"\nBug Project ID: " + results.getString(9)
				);
				
			}
			
		}
		catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
			
		}
		
		return bugID;
		
	}

}
