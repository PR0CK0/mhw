package cs317.project.mhw.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs317.project.mhw.item.Monster;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Class to fetch monsters from the database and store it in an arraylist.
 * 
 * Provides search functionality as well.
 *
 */

public class MonsterList 
{
	private ObservableList<Monster> monsters;
	private Statement statement = Connect.statement;
	private ResultSet set;
	
	/**
	 * Default constructor.
	 */
	public MonsterList()
	{
		populateMonsterList();
	}
	
	/**
	 * Reusability at its finest. Gets every monster given a query.
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
					monsters.add(new Monster(set.getString("name"), set.getString("type"), set.getInt("poisonWeakness"),
							set.getInt("sleepWeakness"), set.getInt("paralysisWeakness"), set.getInt("blastWeakness"),
							set.getInt("stunWeakness")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find monsters from database.");
		}
	}

	/**
	 * Populates the monster array list with every armor piece.
	 */
	public void populateMonsterList()
	{
		monsters = FXCollections.observableArrayList();
		select("SELECT * FROM Monster");
	}
	
	/**
	 * Searches the monster relation to return a subset that match the search term.
	 * 
	 * @param searchTerm
	 */
	public void search(String searchTerm)
	{
		monsters = FXCollections.observableArrayList();
		select("SELECT * FROM Monster WHERE name LIKE '%" + searchTerm + "%'");
	}

	public ObservableList<Monster> getMonsterList() {
		return monsters;
	}
}