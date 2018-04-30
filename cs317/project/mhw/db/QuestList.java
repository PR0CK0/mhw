package cs317.project.mhw.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs317.project.mhw.item.Quest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Class to fetch armor from the database and store it in an arraylist.
 * 
 * Provides search functionality as well.
 *
 */

public class QuestList 
{
	private ObservableList<Quest> quests;
	private Statement statement = Connect.statement;
	private ResultSet set;
	
	/**
	 * Default constructor.
	 */
	public QuestList()
	{
		populateQuestList();
	}
	
	/**
	 * Reusability at its finest. Gets every quest given a query.
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
					quests.add(new Quest(set.getString("name"), set.getInt("level"), set.getString("targetMonster"), set.getInt("hr"),
							set.getInt("rewardMoney")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find quests from database.");
		}
	}

	/**
	 * Populates the quest array list with every armor piece.
	 */
	public void populateQuestList()
	{
		quests = FXCollections.observableArrayList();
		select("SELECT * FROM Quest");
	}
	
	/**
	 * Searches the quest relation to return a subset that match the search term. Can
	 * also search with different types: the quest name or the quest's target monster.
	 * 
	 * @param searchTerm
	 * @param searchType
	 */
	public void search(String searchTerm, String searchType)
	{
		quests = FXCollections.observableArrayList();
		if (searchType.equalsIgnoreCase("name"))
		{
			select("SELECT * FROM Quest WHERE name LIKE '%" + searchTerm + "%'");
		}
		else if (searchType.equalsIgnoreCase("monster"))
		{
			select("SELECT * FROM Quest WHERE targetMonster LIKE '%" + searchTerm + "%'");
		}
	}	
	
	public ObservableList<Quest> getQuestList() {
		return quests;
	}
}