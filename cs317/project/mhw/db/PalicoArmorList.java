package cs317.project.mhw.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs317.project.mhw.item.PalicoArmor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Class to fetch palico armor from the database and store it in an arraylist.
 * 
 * Provides search functionality as well.
 *
 */

public class PalicoArmorList 
{
	private ObservableList<PalicoArmor> palicoArmor;
	private Statement statement = Connect.statement;
	private ResultSet set;
	
	/**
	 * Default constructor.
	 */
	public PalicoArmorList()
	{
		populatePalicoArmorList();
	}
	
	/**
	 * Reusability at its finest. Gets every palico armor piece given a query.
	 * 
	 * @param query
	 * @param armorType
	 */
	private void select(String query, String armorType)
	{
		try 
		{
			set = statement.executeQuery(query);
			while(set.next())
			{	
				palicoArmor.add(new PalicoArmor(new String(armorType), set.getString("name"), set.getInt("rarity"), set.getInt("defense"),
						set.getInt("vsFire"), set.getInt("vsWater"), set.getInt("vsThunder"), set.getInt("vsIce"), 
						set.getInt("vsDragon")));
			}	

		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find palico armor from database.");
		}
	}
	
	/**
	 * Populates the palico armor array list with every armor piece.
	 */
	public void populatePalicoArmorList()
	{
		palicoArmor = FXCollections.observableArrayList();
		select("SELECT * FROM Palico_Head", "head");
		select("SELECT * FROM Palico_Chest", "chest");
	}
	
	/**
	 * Searches the palico armor relation to return a subset that match the search term.
	 * 
	 * @param searchTerm
	 */
	public void search(String searchTerm)
	{
		palicoArmor = FXCollections.observableArrayList();
		select("SELECT * FROM Palico_Head WHERE name LIKE '%" + searchTerm + "%'", "head");
		select("SELECT * FROM Palico_Chest WHERE name LIKE '%" + searchTerm + "%'", "chest");
	}
	
	public ObservableList<PalicoArmor> getPalicoArmorList() {
		return palicoArmor;
	}
}