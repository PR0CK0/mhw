package cs317.project.mhw.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs317.project.mhw.item.PalicoWeapon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Class to fetch palico weapons from the database and store it in an arraylist.
 * 
 * Provides search functionality as well.
 *
 */

public class PalicoWeaponList 
{
	private ObservableList<PalicoWeapon> palicoWeapons;
	private Statement statement = Connect.statement;
	private ResultSet set;
	
	/**
	 * Default constructor.
	 */
	public PalicoWeaponList()
	{
		populatePalicoWeaponList();
	}
	
	/**
	 * Reusability at its finest. Gets every palico weapon given a query.
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
					palicoWeapons.add(new PalicoWeapon(set.getString("name"), set.getInt("rarity"), set.getInt("attackMelee"), set.getInt("attackRanged"), 
							set.getString("attackType"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("Affinity"), 
							set.getInt("defenseBonus"), set.getString("elderSeal")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find palico weapons from database.");
		}
	}

	/**
	 * Populates the palico weapon array list with every armor piece.
	 */
	public void populatePalicoWeaponList()
	{
		palicoWeapons = FXCollections.observableArrayList();
		select("SELECT * FROM Palico_Weapon");
	}

	/**
	 * Searches the palico weapon relation to return a subset that match the search term.
	 * 
	 * @param searchTerm
	 */
	public void search(String searchTerm)
	{
		palicoWeapons = FXCollections.observableArrayList();
		select("SELECT * FROM Palico_Weapon WHERE name LIKE '%" + searchTerm + "%'");
	}
	
	public ObservableList<PalicoWeapon> getPalicoWeaponList() {
		return palicoWeapons;
	}
}