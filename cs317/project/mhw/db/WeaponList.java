package cs317.project.mhw.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cs317.project.mhw.item.Weapon_Bow;
import cs317.project.mhw.item.Weapon_ChargeBlade;
import cs317.project.mhw.item.Weapon_DualBlades;
import cs317.project.mhw.item.Weapon_Greatsword;
import cs317.project.mhw.item.Weapon_Gunlance;
import cs317.project.mhw.item.Weapon_Hammer;
import cs317.project.mhw.item.Weapon_HeavyBowgun;
import cs317.project.mhw.item.Weapon_Huntinghorn;
import cs317.project.mhw.item.Weapon_InsectGlaive;
import cs317.project.mhw.item.Weapon_Kinsect;
import cs317.project.mhw.item.Weapon_Lance;
import cs317.project.mhw.item.Weapon_LightBowgun;
import cs317.project.mhw.item.Weapon_Longsword;
import cs317.project.mhw.item.Weapon_SwitchAxe;
import cs317.project.mhw.item.Weapon_SwordAndShield;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Class to fetch weapons from the database and store it in an arraylist.
 * No need for populate methods, just search methods with an empty search
 * term will do.
 * 
 * Provides search functionality as well.
 *
 */

public class WeaponList 
{
	private ObservableList<Weapon_Bow> bows;
	private ObservableList<Weapon_ChargeBlade> chargeblades;
	private ObservableList<Weapon_DualBlades> dualblades;
	private ObservableList<Weapon_Greatsword> greatswords;
	private ObservableList<Weapon_Gunlance> gunlances;
	private ObservableList<Weapon_Hammer> hammers;
	private ObservableList<Weapon_HeavyBowgun> hbgs;
	private ObservableList<Weapon_Huntinghorn> huntinghorns;
	private ObservableList<Weapon_InsectGlaive> glaives;
	private ObservableList<Weapon_Kinsect> kinsects;
	private ObservableList<Weapon_Lance> lances;
	private ObservableList<Weapon_LightBowgun> lbgs;
	private ObservableList<Weapon_Longsword> longswords;
	private ObservableList<Weapon_SwitchAxe> switchaxes;
	private ObservableList<Weapon_SwordAndShield> snss;
	private Statement statement = Connect.statement;
	private ResultSet set;

	/**
	 * Default constructor.
	 */
	public WeaponList()
	{
		searchGreatsword("");
		searchLongsword("");
		searchSwordAndShield("");
		searchDualBlades("");
		searchSwitchAxe("");
		searchChargeBlade("");
		searchLance("");
		searchGunlance("");
		searchInsectGlaive("");
		searchKinsect("");
		searchHammer("");
		searchHuntinghorn("");
		searchLightBowgun("");
		searchHeavyBowgun("");
		searchBow("");
	}
	
	/**
	 * Search method for greatswords.
	 * 
	 * @param searchTerm
	 */
	public void searchGreatsword(String searchTerm)
	{
		greatswords = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_Greatsword WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					greatswords.add(new Weapon_Greatsword(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
							set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("elderSeal")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find greatswords from database.");
		}
	}

	/**
	 * Search method for longswords.
	 * 
	 * @param searchTerm
	 */
	public void searchLongsword(String searchTerm)
	{
		longswords = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_Longsword WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					longswords.add(new Weapon_Longsword(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
							set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("elderSeal")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find longswords from database.");
		}
	}
	
	/**
	 * Search method for sword and shields.
	 * 
	 * @param searchTerm
	 */
	public void searchSwordAndShield(String searchTerm)
	{
		snss = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_SwordAndShield WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					snss.add(new Weapon_SwordAndShield(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
							set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("elderSeal")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find sword and shields from database.");
		}
	}
	
	/**
	 * Search method for dual blades.
	 * 
	 * @param searchTerm
	 */
	public void searchDualBlades(String searchTerm)
	{
		dualblades = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_DualBlades WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
				dualblades.add(new Weapon_DualBlades(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
						set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getString("element2Type"),
						set.getInt("element2Damage"), set.getInt("hiddenElement"), set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), 
						set.getInt("lvl3Gem"), set.getInt("defenseBonus"), set.getString("elderSeal")));	
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find dual blades from database.");
		}
	}
	
	/**
	 * Search method for switch axes.
	 * 
	 * @param searchTerm
	 */
	public void searchSwitchAxe(String searchTerm)
	{
		switchaxes = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_SwitchAxe WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
				switchaxes.add(new Weapon_SwitchAxe(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
						set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
						set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
						set.getString("elderSeal"), set.getString("phialType"), set.getInt("phialDamage")));	
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find switchaxes from database.");
		}
	}	
	
	/**
	 * Search method for charge blades.
	 * 
	 * @param searchTerm
	 */
	public void searchChargeBlade(String searchTerm)
	{
		chargeblades = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_ChargeBlade WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
				chargeblades.add(new Weapon_ChargeBlade(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
						set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
						set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
						set.getString("elderSeal"), set.getString("phialType")));	
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find chargeblades from database.");
		}
	}	
	
	/**
	 * Search method for lances.
	 * 
	 * @param searchTerm
	 */
	public void searchLance(String searchTerm)
	{
		lances = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_Lance WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					lances.add(new Weapon_Lance(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
							set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("elderSeal")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find lances from database.");
		}
	}
	
	/**
	 * Search method for gunlance.
	 * 
	 * @param searchTerm
	 */
	public void searchGunlance(String searchTerm)
	{
		gunlances = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_Gunlance WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					gunlances.add(new Weapon_Gunlance(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
							set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("elderSeal"), set.getString("shellingType"), set.getInt("shellingLevel")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find gunlances from database.");
		}
	}
	
	/**
	 * Search method for insect glaives.
	 * 
	 * @param searchTerm
	 */
	public void searchInsectGlaive(String searchTerm)
	{
		glaives = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_InsectGlaive WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					glaives.add(new Weapon_InsectGlaive(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
							set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("elderSeal"), set.getString("kinsect Bonus")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find insect glaives from database.");
		}
	}
	
	/**
	 * Search method for kinsects.
	 * 
	 * @param searchTerm
	 */
	public void searchKinsect(String searchTerm)
	{
		kinsects = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_Kinsect WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					kinsects.add(new Weapon_Kinsect(set.getString("name"), set.getInt("rarity"), set.getString("attackType"), 
							set.getString("dustEffect"), set.getInt("powerLvl"), set.getInt("speedLvl"), set.getInt("healLvl")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find kinsects from database.");
		}
	}
	
	/**
	 * Search method for hammers.
	 * 
	 * @param searchTerm
	 */
	public void searchHammer(String searchTerm)
	{
		hammers = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_Hammer WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					hammers.add(new Weapon_Hammer(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
							set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("elderSeal")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find hammers from database.");
		}
	}
	
	/**
	 * Search method for huntinghorns.
	 * 
	 * @param searchTerm
	 */
	public void searchHuntinghorn(String searchTerm)
	{
		huntinghorns = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_HuntingHorn WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					huntinghorns.add(new Weapon_Huntinghorn(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getString("sharpness"), 
							set.getInt("affinity"), set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), 
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("elderSeal"), set.getString("note1"), set.getString("note2"), set.getString("note3")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find huntinghorns from database.");
		}
	}
	
	/**
	 * Search method for light bowguns.
	 * 
	 * @param searchTerm
	 */
	public void searchLightBowgun(String searchTerm)
	{
		lbgs = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_LightBowgun WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					lbgs.add(new Weapon_LightBowgun(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getInt("affinity"),  
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("deviation"), set.getInt("modSlots"), 
							set.getInt("normal1"), set.getInt("normal2"), set.getInt("normal3"),
							set.getInt("piercing1"), set.getInt("piercing2"), set.getInt("piercing3"),
							set.getInt("spread1"), set.getInt("spread2"), set.getInt("spread3"), 
							set.getInt("sticky1"), set.getInt("sticky2"), set.getInt("sticky3"), 
							set.getInt("cluster1"), set.getInt("cluster2"), set.getInt("cluster3"), 
							set.getInt("recover1"), set.getInt("recover2"), set.getInt("recover3"), 
							set.getInt("poison1"), set.getInt("poison2"), set.getInt("poison3"),
							set.getInt("paralysis1"), set.getInt("paralysis2"), set.getInt("paralysis3"),
							set.getInt("sleep1"), set.getInt("sleep2"), set.getInt("sleep3"),
							set.getInt("exhaust1"), set.getInt("exhaust2"), set.getInt("exhaust3"),
							set.getInt("flaming"), set.getInt("water"), set.getInt("freeze"),
							set.getInt("thunder"), set.getInt("dragon"), set.getInt("slicing"),
							set.getInt("wyvern"), set.getInt("demon"), set.getInt("armor"), set.getInt("tranq")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find light bowguns from database.");
		}
	}
	
	/**
	 * Search method for heavy bowguns.
	 * 
	 * @param searchTerm
	 */
	public void searchHeavyBowgun(String searchTerm)
	{
		hbgs = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_HeavyBowgun WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					hbgs.add(new Weapon_HeavyBowgun(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getInt("affinity"),  
							set.getInt("lvl1Gem"), set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), 
							set.getString("deviation"), set.getInt("modSlots"), set.getString("specialAmmo"),
							set.getInt("normal1"), set.getInt("normal2"), set.getInt("normal3"),
							set.getInt("piercing1"), set.getInt("piercing2"), set.getInt("piercing3"),
							set.getInt("spread1"), set.getInt("spread2"), set.getInt("spread3"), 
							set.getInt("sticky1"), set.getInt("sticky2"), set.getInt("sticky3"), 
							set.getInt("cluster1"), set.getInt("cluster2"), set.getInt("cluster3"), 
							set.getInt("recover1"), set.getInt("recover2"), set.getInt("recover3"), 
							set.getInt("poison1"), set.getInt("poison2"), set.getInt("poison3"),
							set.getInt("paralysis1"), set.getInt("paralysis2"), set.getInt("paralysis3"),
							set.getInt("sleep1"), set.getInt("sleep2"), set.getInt("sleep3"),
							set.getInt("exhaust1"), set.getInt("exhaust2"), set.getInt("exhaust3"),
							set.getInt("flaming"), set.getInt("water"), set.getInt("freeze"),
							set.getInt("thunder"), set.getInt("dragon"), set.getInt("slicing"),
							set.getInt("wyvern"), set.getInt("demon"), set.getInt("armor"), set.getInt("tranq")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find heavy bowguns from database.");
		}
	}
	
	/**
	 * Search method for bows.
	 * 
	 * @param searchTerm
	 */
	public void searchBow(String searchTerm)
	{
		bows = FXCollections.observableArrayList();
		try 
		{
			set = statement.executeQuery("SELECT * FROM Weapon_Bow WHERE name LIKE '%" + searchTerm + "%'");
			while(set.next())
			{	
					bows.add(new Weapon_Bow(set.getString("name"), set.getInt("rarity"), set.getInt("attack"), set.getInt("affinity"),  
							set.getString("elementType"), set.getInt("elementDamage"), set.getInt("hiddenElement"), set.getInt("lvl1Gem"), 
							set.getInt("lvl2Gem"), set.getInt("lvl3Gem"), set.getInt("defenseBonus"), set.getString("elderSeal"), 
							set.getInt("closeRange"), set.getInt("power"), set.getInt("paralysis"), set.getInt("poison"), 
							set.getInt("sleep"), set.getInt("blast")));
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.err.println("Failed to find bows from database.");
		}
	}

	public ObservableList<Weapon_Greatsword> getGreatswordList() {
		return greatswords;
	}
	public ObservableList<Weapon_Longsword> getLongswordList() {
		return longswords;
	}
	public ObservableList<Weapon_SwordAndShield> getSwordAndShieldList() {
		return snss;
	}
	public ObservableList<Weapon_DualBlades> getDualBladesList() {
		return dualblades;
	}
	public ObservableList<Weapon_SwitchAxe> getSwitchAxeList() {
		return switchaxes;
	}
	public ObservableList<Weapon_ChargeBlade> getChargeBladeList() {
		return chargeblades;
	}
	public ObservableList<Weapon_Lance> getLanceList() {
		return lances;
	}
	public ObservableList<Weapon_Gunlance> getGunlanceList() {
		return gunlances;
	}
	public ObservableList<Weapon_InsectGlaive> getInsectGlaiveList() {
		return glaives;
	}
	public ObservableList<Weapon_Kinsect> getKinsectList() {
		return kinsects;
	}
	public ObservableList<Weapon_Hammer> getHammerList() {
		return hammers;
	}
	public ObservableList<Weapon_Huntinghorn> getHuntinghornList() {
		return huntinghorns;
	}
	public ObservableList<Weapon_LightBowgun> getLightBowgunList() {
		return lbgs;
	}
	public ObservableList<Weapon_HeavyBowgun> getHeavyBowgunList() {
		return hbgs;
	}
	public ObservableList<Weapon_Bow> getBowList() {
		return bows;
	}
}