package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Weapon_Bow object class. Stores all data for an instance of the class.
 * 
 * Extends: Weapon
 *
 */

public class Weapon_Bow extends Weapon 
{
	private int attack;
	private int affinity;
	private String elementType;
	private int elementDamage;
	private int hiddenElement;
	private int level1Gem;
	private int level2Gem;
	private int level3Gem;
	private int defenseBonus;
	private String elderseal;
	private int closeRange;
	private int power;
	private int paralysis;
	private int poison;
	private int sleep;
	private int blast;
	
	/**
	 * Default constructor.
	 * 
	 * @param name
	 * @param rarity
	 * @param attack
	 * @param affinity
	 * @param elementType
	 * @param elementDamage
	 * @param hiddenElement
	 * @param level1Gem
	 * @param level2Gem
	 * @param level3Gem
	 * @param defenseBonus
	 * @param elderseal
	 * @param closeRange
	 * @param power
	 * @param paralysis
	 * @param poison
	 * @param sleep
	 * @param blast
	 */
	public Weapon_Bow(String name, int rarity, int attack, int affinity, String elementType, int elementDamage, int hiddenElement, 
			int level1Gem, int level2Gem, int level3Gem, int defenseBonus, String elderseal, 
			int closeRange, int power, int paralysis, int poison, int sleep, int blast)
	{
		type = "bow";
		this.name = name;
		this.rarity = rarity;
		
		this.attack = attack;
		this.affinity = affinity;
		this.elementType = elementType;
		this.elementDamage = elementDamage;
		this.hiddenElement = hiddenElement;
		this.level1Gem = level1Gem;
		this.level2Gem = level2Gem;
		this.level3Gem = level3Gem;
		this.defenseBonus = defenseBonus;
		this.elderseal = elderseal;
		this.closeRange = closeRange;
		this.power = power;
		this.poison = poison;
		this.sleep = sleep;
		this.blast = blast;
	}
	
	@Override
	public String getAllInfo() {
		allInfo = 
				"Rarity: " + rarity + "\n\n"
				
				+ "Attack: " + attack + "\n\n"
				+ "Affinity: " + affinity + "%\n\n"

				+ "Element: " + elementType + ", with a damage of " + elementDamage + "\n"
				+ "Hidden element: " + hiddenElement + "\n\n"
				
				+ "Close range shot level: " + closeRange + "\n"
				+ "Power shot level: " + power + "\n"
				+ "Poison shot level: " + poison + "\n"
				+ "Sleep shot level: " + sleep + "\n"
				+ "Blast shot level: " + blast + "\n"
				
				+ "Level 1 gem slots: " + level1Gem + "\n"
				+ "Level 2 gem slots: " + level2Gem + "\n"
				+ "Level 3 gem slots: " + level3Gem + "\n\n"
				
				+ "Defense bonus: " + defenseBonus + "\n\n"
				
				+ "Elderseal: " + elderseal + "\n";
		
		return allInfo;
	}
	
	@Override
	public int getAttack() {
		return attack;
	}

	@Override
	public int getAffinity() {
		return affinity;
	}

	public String getElementType() {
		return elementType;
	}

	public int getElementDamage() {
		return elementDamage;
	}

	public int getHiddenElement() {
		return hiddenElement;
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

	@Override
	public int getDefenseBonus() {
		return defenseBonus;
	}

	public String getElderseal() {
		return elderseal;
	}

	public int getCloseRange() {
		return closeRange;
	}

	public int getPower() {
		return power;
	}

	public int getParalysis() {
		return paralysis;
	}

	public int getPoison() {
		return poison;
	}

	public int getSleep() {
		return sleep;
	}

	public int getBlast() {
		return blast;
	}
}