package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Weapon_Kinsect object class. Stores all data for an instance of the class.
 * 
 * Extends: Weapon
 *
 */

public class Weapon_Kinsect extends Weapon
{
	private String attackType;
	private String dustEffect;
	private int powerLevel;
	private int speedLevel;
	private int healLevel;
	
	/**
	 * Default constructor.
	 * 
	 * @param name
	 * @param rarity
	 * @param attackType
	 * @param dustEffect
	 * @param powerLevel
	 * @param speedLevel
	 * @param healLevel
	 */
	public Weapon_Kinsect(String name, int rarity, String attackType, String dustEffect, int powerLevel,
			int speedLevel, int healLevel)
	{
		type = "kinsect";
		this.name = name;
		this.rarity = rarity;
		
		this.attackType = attackType;
		this.dustEffect = dustEffect;
		this.powerLevel = powerLevel;
		this.speedLevel = speedLevel;
		this.healLevel = healLevel;
	}

	@Override
	public String getAllInfo()
	{
		allInfo = 
				"Rarity: " + rarity + "\n\n"
				
				+ "Attack type: " + attackType + "\n\n"
				+ "Dust effect: " + dustEffect + "\n"
				+ "Power level: " + powerLevel + "\n"
				+ "Speed level: " + speedLevel + "\n"
				+ "Heal level: " + healLevel + "\n";
		
		return allInfo;
	}
	
	public String getAttackType() {
		return attackType;
	}

	public String getDustEffect() {
		return dustEffect;
	}

	public int getPowerLevel() {
		return powerLevel;
	}

	public int getSpeedLevel() {
		return speedLevel;
	}

	public int getHealLevel() {
		return healLevel;
	}
}