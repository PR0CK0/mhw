package cs317.project.mhw.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs317.project.mhw.item.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Class to fetch items from the database and store it in an arraylist.
 * 
 * Provides search functionality as well.
 *
 */

public class ItemList 
{
	private ObservableList<Item> items;
	private Statement statement = Connect.statement;
	private ResultSet set;
	
	/**
	 * Default constructor.
	 */
	public ItemList()
	{
		populateItemList();
	}
	
	/**
	 * Reusability at its finest. Gets every item given a query.
	 * 
	 * @param query
	 */
	private void select(String query)
	{
		try 
		{
			set = statement.executeQuery(query);
			while(set.next())
			{	
					items.add(new Item(set.getString("name"), set.getInt("capacity"), set.getString("description")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find items from database.");
		}
	}

	/**
	 * Populates the item array list with every armor piece.
	 */
	public void populateItemList()
	{
		items = FXCollections.observableArrayList();
		select("SELECT * FROM Item");
	}

	/**
	 * Searches the item relation to return a subset that match the search term.
	 * 
	 * @param searchTerm
	 */
	public void search(String searchTerm)
	{
		items = FXCollections.observableArrayList();
		select("SELECT * FROM Item WHERE name LIKE '%" + searchTerm + "%'");
	}
	
	public ObservableList<Item> getItemList() {
		return items;
	}
}
