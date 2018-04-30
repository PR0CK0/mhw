package cs317.project.mhw.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Tyler Procko
 * @date 10/2017 - 11/2017
 * 
 * Prepares a connection to the server and database specified within the default constructor.
 * 
 */

public class Connect 
{
	/** Prepares a connection to the database. */
	private Connection connection = null;
	
	/** Creates a statement in order to speak with the database. */
	public static Statement statement = null;
	
	/** Stores query results. */
	public static ResultSet set;
	
	// Bad idea to store this stuff openly... must use a .properties file if it is 
	// desired to make this application publishable.
	private String url = "jdbc:mysql://somethingGoesHereButIWon'tTellYou?autoReconnect=true&useSSL=false";
	private String username = "nope";
	private String password = "not tellin you";

	/**
	 * Default constructor.
	 * Sets up a connection to the specified database and creates a statement for use.
	 */
	public Connect() 
	{
		try
		{
			// Attempt connection to the database and create an SQL statement
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			
			// Sucess!
			System.out.println("Connection to database successful!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Database connection failed!");
		}
	}
	
	/**
	 * Method to close the connection to the database.
	 */
	public void disconnect()
	{
		// Connection must exist to disconnect
		if (!connection.equals(null))
		{
			try 
			{
				connection.close();
				System.out.println("Database connection closed!");
			} 
			catch (SQLException e) 
			{
				System.err.println("Error in closing database connection.");
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	public Statement getStatement() {
		return statement;
	}
}
