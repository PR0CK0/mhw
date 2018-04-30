package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Weapon_DualBlades object class. Stores all data for an instance of the class.
 * 
 * Extends: Weapon
 *
 */

public class Weapon_DualBlades extends Weapon
{
	private int attack;
	private String sharpness;
	private int affinity;
	private String elementType;
	private int elementDamage;
	private String element2Type;
	private int element2Damage;
	private int hiddenElement;
	private int level1Gem;
	private int level2Gem;
	private int level3Gem;
	private int defenseBonus;
	private String elderseal;
	
	/**
	 * Default constructor.
	 * 
	 * @param name
	 * @param rarity
	 * @param attack
	 * @param sharpness
	 * @param affinity
	 * @param elementType
	 * @param elementDamage
	 * @param element2Type
	 * @param element2Damage
	 * @param hiddenElement
	 * @param level1Gem
	 * @param level2Gem
	 * @param level3Gem
	 * @param defenseBonus
	 * @param elderseal
	 */
	public Weapon_DualBlades(String name, int rarity, int attack, String sharpness, int affinity,
			String elementType, int elementDamage, String element2Type, int element2Damage, int hiddenElement, 
			int level1Gem, int level2Gem, int level3Gem, int defenseBonus, String elderseal)
	{
		type = "dualblades";
		this.name = name;
		this.rarity = rarity;
		
		this.attack = attack;
		this.sharpness = sharpness;
		this.affinity = affinity;
		this.elementType = elementType;
		this.elementDamage = elementDamage;
		this.element2Type = element2Type;
		this.element2Damage = element2Damage;
		this.hiddenElement = hiddenElement;
		this.level1Gem = level1Gem;
		this.level2Gem = level2Gem;
		this.level3Gem = level3Gem;
		this.defenseBonus = defenseBonus;
		this.elderseal = elderseal;
	}
	
	@Override
	public String getAllInfo() {
		allInfo = 
				"Rarity: " + rarity + "\n\n"
				
				+ "Attack: " + attack + "\n"
				+ "Sharpness: " + sharpness + "\n"
				+ "Affinity: " + affinity + "%\n\n"
				
				+ "Element: " + elementType + ", with a damage of " + elementDamage + "\n"
				+ "Element two: " + element2Type + ", with a damage of " + element2Damage + "\n"
				+ "Hidden element: " + hiddenElement + "\n\n"
				
				
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

	public String getSharpness() {
		return sharpness;
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

	public String getElement2Type() {
		return element2Type;
	}

	public int getElement2Damage(){
		return element2Damage;
	}

	public int getHiddenElement() {
		return hiddenElement;
	}

	public int getLevel1Gem(){
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
}