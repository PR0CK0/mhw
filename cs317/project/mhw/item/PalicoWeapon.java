package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * PalicoWeapon object class. Stores all data for an instance of the class.
 *
 */

public class PalicoWeapon 
{
	private String name;
	private int rarity;
	private int attackMelee;
	private int attackRanged;
	private String attackType;
	private String elementType;
	private int elementDamage;
	private int affinity;
	private int defenseBonus;
	private String elderSeal;
	
	/**
	 * Default constructor.
	 * 
	 * @param name
	 * @param rarity
	 * @param attackMelee
	 * @param attackRanged
	 * @param attackType
	 * @param elementType
	 * @param elementDamage
	 * @param affinity
	 * @param defenseBonus
	 * @param elderSeal
	 */
	public PalicoWeapon(String name, int rarity, int attackMelee, int attackRanged, String attackType,
			String elementType, int elementDamage, int affinity, int defenseBonus, String elderSeal)
	{
		this.name = name;
		this.rarity = rarity;
		this.attackMelee = attackMelee;
		this.attackRanged = attackRanged;
		this.attackType = attackType;
		this.elementType = elementType;
		this.elementDamage = elementDamage;
		this.affinity = affinity;
		this.defenseBonus = defenseBonus;
		this.elderSeal = elderSeal;
	}
	
	public String getName() {
		return name;
	}

	public int getRarity() {
		return rarity;
	}

	public int getAttackMelee() {
		return attackMelee;
	}

	public int getAttackRanged() {
		return attackRanged;
	}

	public String getAttackType() {
		return attackType;
	}

	public String getElementType() {
		return elementType;
	}

	public int getElementDamage() {
		return elementDamage;
	}

	public int getAffinity() {
		return affinity;
	}

	public int getDefenseBonus() {
		return defenseBonus;
	}

	public String getElderSeal() {
		return elderSeal;
	}
}
