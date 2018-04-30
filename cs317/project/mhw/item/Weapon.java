package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Weapon object class. Stores all data abstracted data for a child of the class.
 * 
 * Children: Weapon_Bow, Weapon_ChargeBlade, Weapon_DualBlades, WeaponGreatsword,
 * Weapon_Gunlance, Weapon_Hammer, Weapon_HeavyBowgun, Weapon_Huntinghorn, 
 * Weapon_InsectGlaive, Weapon_Kinsect, Weapon_Lance, Weapon_LightBowgun,
 * Weapon_Longsword, Weapon_SwitchAxe, Weapon_SwordAndShield
 *
 */

public class Weapon 
{
	protected String type;
	
	protected String name;
	protected int rarity;
	protected String allInfo;
	
	protected int attack;
	protected int defenseBonus;
	protected int affinity;

	/**
	 * Dummy constructor for the ArmorBuilder class.
	 */
	public Weapon()
	{
		attack = 0;
		defenseBonus = 0;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public int getRarity() 
	{
		return rarity;
	}
	
	/**
	 * Very important to display all info about any weapon.
	 * 
	 * @return all info specific to any Weapon subclass
	 */
	public String getAllInfo()
	{
		return null;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public int getDefenseBonus()
	{
		return defenseBonus;
	}
	
	public int getAffinity()
	{
		return affinity;
	}
}
