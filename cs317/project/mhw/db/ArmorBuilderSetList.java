package cs317.project.mhw.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cs317.project.mhw.item.ArmorBuilderSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * TODO
 * Class intended to provide full functionality in fetching and updating/deleting
 * stored armorsets created by a user in ArmorBuilder. As of now, only sets stored
 * in the database can be retrieved. Sets cannot be saved.
 *
 */

public class ArmorBuilderSetList 
{
	private ObservableList<ArmorBuilderSet> armorsets;
	private Statement statement = Connect.statement;
	private ResultSet set;
	
	/**
	 * Default constructor.
	 */
	public ArmorBuilderSetList()
	{
		populateArmorBuilderSetList();
	}
	
	/**
	 * SQL method to grab all armor builder sets from the database.
	 */
	public void populateArmorBuilderSetList()
	{
		armorsets = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM ArmorSets");
			while(set.next())
			{	
				armorsets.add(new ArmorBuilderSet(set.getString("setName"), set.getString("weaponName"), set.getString("headName"),
						set.getString("chestName"), set.getString("armsName"), set.getString("waistName"), set.getString("legsName"),
						set.getString("charmName")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find your armor sets from database.");
		}
	}
	
	/**
	 * TODO
	 * SQL method to add a new armor set to the relation. Not done. Would need to take in a weapon, head armor,
	 * chest armor, arms armor, waist armor, legs armor and a charm from the armor builder.
	 */
	public void saveNewArmorSet()
	{
		try 
		{
			statement.executeUpdate("INSERT INTO "
				+ "ArmorSETS (weaponName, headName, chestName, armsName, waistName, legsName, charmName) "
				+ "VALUES("
				+ "dummyWeapon, "
				+ "dummyHead, "
				+ "dummyChest, " 
				+ "dummyArms, "
				+ "dummyWaist, "
				+ "dummyLegs, "
				+ "dummyCharm)");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to save your armor set to the database.");
		}
	}
	
	public ObservableList<ArmorBuilderSet> getArmorBuilderSetList() {
		return armorsets;
	}
}