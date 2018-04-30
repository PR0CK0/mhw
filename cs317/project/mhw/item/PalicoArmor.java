package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * PalicoArmor object class. Stores all data for an instance of the class.
 *
 */

public class PalicoArmor 
{
	private String type;
	
	private String name;
	private int rarity;
	private int defense;
	private int vsFire;
	private int vsWater;
	private int vsThunder;
	private int vsIce;
	private int vsDragon;
	
	/**
	 * Default constructor.
	 * 
	 * @param type
	 * @param name
	 * @param rarity
	 * @param defense
	 * @param vsFire
	 * @param vsWater
	 * @param vsThunder
	 * @param vsIce
	 * @param vsDragon
	 */
	public PalicoArmor(String type, String name, int rarity, int defense, int vsFire, int vsWater,
			int vsThunder, int vsIce, int vsDragon)
	{
		this. type = type;
		
		this.name = name;
		this.rarity = rarity;
		this.defense = defense;
		this.vsFire = vsFire;
		this.vsWater = vsWater;
		this.vsThunder = vsThunder;
		this.vsIce = vsIce;
		this.vsDragon = vsDragon;
	}
	
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public int getRarity() {
		return rarity;
	}

	public int getDefense() {
		return defense;
	}

	public int getVsFire() {
		return vsFire;
	}

	public int getVsWater() {
		return vsWater;
	}

	public int getVsThunder() {
		return vsThunder;
	}

	public int getVsIce() {
		return vsIce;
	}

	public int getVsDragon() {
		return vsDragon;
	}
}
