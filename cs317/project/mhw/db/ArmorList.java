package cs317.project.mhw.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs317.project.mhw.item.Armor;
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

public class ArmorList 
{
	private ObservableList<Armor> armor;
	private Statement statement = Connect.statement;
	private ResultSet set;
	
	/**
	 * Default constructor.
	 */
	public ArmorList()
	{
		populateArmorList();
	}
	
	/**
	 * Reusability at its finest. Gets every armor piece given a query.
	 * 
	 * @param query
	 * @param type
	 */
	private void select(String query, String type)
	{
		try 
		{
			set = statement.executeQuery(query);
			while(set.next())
			{	
				armor.add(new Armor(new String(type), set.getString("Name"), set.getInt("Rarity"), set.getInt("Defense"), 
						set.getInt("Lvl1Gem"), set.getInt("Lvl2Gem"), set.getInt("Lvl3Gem"), set.getInt("vsFire"),
						set.getInt("vsWater"), set.getInt("vsThunder"), set.getInt("vsIce"), set.getInt("vsDragon"),
						set.getString("Skill1"), set.getInt("Skill1Level"), set.getString("Skill2"), set.getInt("Skill2Level"),
						set.getString("2PieceSetBonus"), set.getString("3PieceSetBonus"), set.getString("4PieceSetBonus")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find armor from database.");
		}
	}
	
	/**
	 * Populates the armor array list with every armor piece.
	 */
	public void populateArmorList()
	{
		armor = FXCollections.observableArrayList();
		select("SELECT * FROM Armor_Head", "head");
		select("SELECT * FROM Armor_Chest", "chest");
		select("SELECT * FROM Armor_Arms", "arms");
		select("SELECT * FROM Armor_Waist", "waist");
		select("SELECT * FROM Armor_Legs", "legs");
	}
	
	/**
	 * Searches the armor relations to return a subset that match the search term. Can
	 * also search with different types: the armor name or the armor skill.
	 * 
	 * @param searchTerm
	 * @param searchType
	 */
	public void search(String searchTerm, String searchType)
	{
		armor = FXCollections.observableArrayList();
		if (searchType.equalsIgnoreCase("name"))
		{
			select("SELECT * FROM Armor_Head WHERE name LIKE '%" + searchTerm + "%'", "head");
			select("SELECT * FROM Armor_Chest WHERE name LIKE '%" + searchTerm + "%'", "chest");
			select("SELECT * FROM Armor_Arms WHERE name LIKE '%" + searchTerm + "%'", "arms");
			select("SELECT * FROM Armor_Waist WHERE name LIKE '%" + searchTerm + "%'", "waist");
			select("SELECT * FROM Armor_Legs WHERE name LIKE '%" + searchTerm + "%'", "legs");
		}
		else if (searchType.equalsIgnoreCase("skill"))
		{
			select("SELECT * FROM Armor_Head WHERE skill1 LIKE '%" + searchTerm + "%' OR skill2 LIKE '%" + searchTerm + "%'", "head");
			select("SELECT * FROM Armor_Chest WHERE skill1 LIKE '%" + searchTerm + "%' OR skill2 LIKE '%" + searchTerm + "%'", "chest");
			select("SELECT * FROM Armor_Arms WHERE skill1 LIKE '%" + searchTerm + "%' OR skill2 LIKE '%" + searchTerm + "%'", "arms");
			select("SELECT * FROM Armor_Waist WHERE skill1 LIKE '%" + searchTerm + "%' OR skill2 LIKE '%" + searchTerm + "%'", "waist");
			select("SELECT * FROM Armor_Legs WHERE skill1 LIKE '%" + searchTerm + "%' OR skill2 LIKE '%" + searchTerm + "%'", "legs");
		}
	}
	
	/**
	 * Used in ArmorBuilder.
	 */
	public void getHeadOnly() {
		armor = FXCollections.observableArrayList();
		select("SELECT * FROM Armor_Head", "head");
	}
	/**
	 * Used in ArmorBuilder.
	 */
	public void getChestOnly() {
		armor = FXCollections.observableArrayList();
		select("SELECT * FROM Armor_Chest", "chest");
	}
	/**
	 * Used in ArmorBuilder.
	 */
	public void getArmsOnly() {
		armor = FXCollections.observableArrayList();
		select("SELECT * FROM Armor_Arms", "arms");
	}
	/**
	 * Used in ArmorBuilder.
	 */
	public void getWaistOnly() {
		armor = FXCollections.observableArrayList();
		select("SELECT * FROM Armor_Waist", "waist");
	}
	/**
	 * Used in ArmorBuilder.
	 */
	public void getLegsOnly() {
		armor = FXCollections.observableArrayList();
		select("SELECT * FROM Armor_Legs", "legs");
	}
	public ObservableList<Armor> getArmorList() {
		return armor;
	}
}

/*TODO
// For use in ArmorBuilder and ArmorBuilderSetsPage
public Armor getSpecificHeadOnly(String name) {
	armor = FXCollections.observableArrayList();
	select("SELECT * FROM Armor_Head WHERE name LIKE '%" + name + "%'", "head");
	Armor a = armor.get(0);
	return a;
}
public Armor getSpecificChestOnly(String name) {
	armor = FXCollections.observableArrayList();
	select("SELECT * FROM Armor_Chest WHERE name LIKE '%" + name + "%'", "chest");
	Armor a = armor.get(0);
	return a;
}
public Armor getSpecificArmsOnly(String name) {
	armor = FXCollections.observableArrayList();
	select("SELECT * FROM Armor_Arms WHERE name LIKE '%" + name + "%'", "arms");
	Armor a = armor.get(0);
	return a;
}
public Armor getSpecificWaistOnly(String name) {
	armor = FXCollections.observableArrayList();
	select("SELECT * FROM Armor_Waist WHERE name LIKE '%" + name + "%'", "waist");
	Armor a = armor.get(0);
	System.out.println(a.getName());
	return a;
}
public Armor getSpecificLegsOnly(String name) {
	armor = FXCollections.observableArrayList();
	select("SELECT * FROM Armor_Legs WHERE name LIKE '%" + name + "%'", "legs");
	Armor a = armor.get(0);
	return a;
}*/