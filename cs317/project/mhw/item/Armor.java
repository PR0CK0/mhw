package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Armor object class. Stores all data for an instance of the class.
 *
 */

public class Armor 
{
	private String type;
	
	private String name;
	private int rarity;
	private int defense;
	private int level1Gem;
	private int level2Gem;
	private int level3Gem;
	private int fireRes;
	private int waterRes;
	private int thunderRes;
	private int iceRes;
	private int dragonRes;
	private String skill1;
	private int skill1Level;
	private String skill2;
	private int skill2Level;
	private String setBonus2Pc;
	private String setBonus3Pc;
	private String setBonus4Pc;
	
	/**
	 * Dummy constructor for the ArmorBuilder.
	 */
	public Armor()
	{
		this.defense = 0;
		this.fireRes = 0;
		this.waterRes = 0;
		this.thunderRes = 0;
		this.iceRes = 0;
		this.dragonRes = 0;
	}
	
	/**
	 * Default constructor. 
	 * 
	 * @param type
	 * @param name
	 * @param rarity
	 * @param defense
	 * @param level1Gem
	 * @param level2Gem
	 * @param level3Gem
	 * @param fireRes
	 * @param waterRes
	 * @param thunderRes
	 * @param iceRes
	 * @param dragonRes
	 * @param skill1
	 * @param skill1Level
	 * @param skill2
	 * @param skill2Level
	 * @param setBonus2Pc
	 * @param setBonus3Pc
	 * @param setBonus4Pc
	 */
	public Armor(String type, String name, int rarity, int defense, int level1Gem,
			int level2Gem, int level3Gem, int fireRes, int waterRes, int thunderRes,
		    int iceRes, int dragonRes, String skill1, int skill1Level, String skill2,
		    int skill2Level, String setBonus2Pc, String setBonus3Pc, String setBonus4Pc)
	{
		this.type = type;
		
		this.name = name;
		this.rarity = rarity;
		this.defense = defense;
		this.level1Gem = level1Gem;
		this.level2Gem = level2Gem;
		this.level3Gem = level3Gem;
		this.fireRes = fireRes;
		this.waterRes = waterRes;
		this.iceRes = iceRes;
		this.dragonRes = dragonRes;
		this.skill1 = skill1;
		this.skill1Level = skill1Level;
		this.skill2 = skill2;
		this.skill2Level = skill2Level;
		this.setBonus2Pc = setBonus2Pc;
		this.setBonus3Pc = setBonus3Pc;
		this.setBonus4Pc = setBonus4Pc;
	}
	
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getRarity(){
		return rarity;
	}

	public int getDefense() {
		return defense;
	}

	public int getLevel1Gem() {
		return level1Gem;
	}

	public int getLevel2Gem() {
		return level2Gem;
	}

	public int getLevel3Gem() {
		return level3Gem;
	}

	public int getFireRes() {
		return fireRes;
	}

	public int getWaterRes() {
		return waterRes;
	}

	public int getThunderRes() {
		return thunderRes;
	}

	public int getIceRes() {
		return iceRes;
	}

	public int getDragonRes() {
		return dragonRes;
	}

	public String getSkill1() {
		return skill1;
	}

	public int getSkill1Level() {
		return skill1Level;
	}

	public String getSkill2() {
		return skill2;
	}

	public int getSkill2Level() {
		return skill2Level;
	}

	public String getSetBonus2Pc() {
		return setBonus2Pc;
	}

	public String getSetBonus3Pc() {
		return setBonus3Pc;
	}

	public String getSetBonus4Pc() {
		return setBonus4Pc;
	}
}
