package cs317.project.mhw.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cs317.project.mhw.item.Charm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Class to fetch charms from the database and store it in an arraylist.
 * 
 * Provides search functionality as well.
 *
 */

public class CharmList 
{
	private ObservableList<Charm> charms;
	private Statement statement = Connect.statement;
	private ResultSet set;
	
	/**
	 * Default constructor.
	 */
	public CharmList()
	{
		populateCharmList();
	}
	
	/**
	 * Reusability at its finest. Gets every charm given a query.
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
				charms.add(new Charm(set.getString("name"), set.getString("skill1"), set.getInt("skill1Lvl"),
						set.getString("skill2"), set.getInt("skill2Lvl")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find armor from database.");
		}
	}
	
	/**
	 * Populates the charm array list with every armor piece.
	 */
	public void populateCharmList()
	{
		charms = FXCollections.observableArrayList();
		select("SELECT * FROM Charm");
	}
	
	/**
	 * Searches the charm relation to return a subset that match the search term. Can
	 * also search with different types: the charm name or the charm skill.
	 * 
	 * @param searchTerm
	 * @param searchType
	 */
	public void search(String searchTerm, String searchType)
	{
		charms = FXCollections.observableArrayList();
		if (searchType.equalsIgnoreCase("name"))
		{
			select("SELECT * FROM Charm WHERE name LIKE '%" + searchTerm + "%'");
		}
		else if (searchType.equalsIgnoreCase("skill"))
		{
			select("SELECT * FROM Charm WHERE skill1 LIKE '%" + searchTerm + "%' OR skill2 LIKE '%" + searchTerm + "%'");
		}
	}
	
	public ObservableList<Charm> getCharmList() {
		return charms;
	}
}

/*TODO
//For use in ArmorBuilder and ArmorBuilderSetsPage
public Charm getSpecificCharm(String name) {
	charms = FXCollections.observableArrayList();
	select("SELECT * FROM Charm WHERE name LIKE '%" + name + "%'");
	Charm c = charms.get(0);
	return c;
}
*/