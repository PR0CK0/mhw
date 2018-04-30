package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Weapon_HeavyBowgun object class. Stores all data for an instance of the class.
 * 
 * Extends: Weapon
 *
 */

public class Weapon_HeavyBowgun extends Weapon
{
	private int attack;
	private int affinity;
	private int level1Gem;
	private int level2Gem;
	private int level3Gem;
	private int defenseBonus;
	private String deviation;
	private int modSlots;
	private String specialAmmo;
	private int normal1;
	private int normal2;
	private int normal3;
	private int piercing1;
	private int piercing2;
	private int piercing3;
	private int spread1;
	private int spread2;
	private int spread3;
	private int sticky1;
	private int sticky2;
	private int sticky3;
	private int cluster1;
	private int cluster2;
	private int cluster3;
	private int recover1;
	private int recover2;
	private int recover3;
	private int poison1;
	private int poison2;
	private int poison3;
	private int paralysis1;
	private int paralysis2;
	private int paralysis3;
	private int sleep1;
	private int sleep2;
	private int sleep3;
	private int exhaust1;
	private int exhaust2;
	private int exhaust3;
	private int flaming;
	private int water;
	private int freeze;
	private int thunder;
	private int dragon;
	private int slicing;
	private int wyvern;
	private int demon;
	private int armor;
	private int tranq;
	
	/**
	 * Default constructor.
	 * 
	 * @param name
	 * @param rarity
	 * @param attack
	 * @param affinity
	 * @param level1Gem
	 * @param level2Gem
	 * @param level3Gem
	 * @param defenseBonus
	 * @param deviation
	 * @param modSlots
	 * @param specialAmmo
	 * @param normal1
	 * @param normal2
	 * @param normal3
	 * @param piercing1
	 * @param piercing2
	 * @param piercing3
	 * @param spread1
	 * @param spread2
	 * @param spread3
	 * @param sticky1
	 * @param sticky2
	 * @param sticky3
	 * @param cluster1
	 * @param cluster2
	 * @param cluster3
	 * @param recover1
	 * @param recover2
	 * @param recover3
	 * @param poison1
	 * @param poison2
	 * @param poison3
	 * @param paralysis1
	 * @param paralysis2
	 * @param paralysis3
	 * @param sleep1
	 * @param sleep2
	 * @param sleep3
	 * @param exhaust1
	 * @param exhaust2
	 * @param exhaust3
	 * @param flaming
	 * @param water
	 * @param freeze
	 * @param thunder
	 * @param dragon
	 * @param slicing
	 * @param wyvern
	 * @param demon
	 * @param armor
	 * @param tranq
	 */
	public Weapon_HeavyBowgun(String name, int rarity, int attack, int affinity, int level1Gem,
			int level2Gem, int level3Gem, int defenseBonus, String deviation, int modSlots,
			String specialAmmo, int normal1, int normal2, int normal3, int piercing1,
			int piercing2, int piercing3, int spread1, int spread2, int spread3,
			int sticky1, int sticky2, int sticky3, int cluster1, int cluster2,
			int cluster3, int recover1, int recover2, int recover3, int poison1,
			int poison2, int poison3, int paralysis1, int paralysis2, int paralysis3,
			int sleep1, int sleep2, int sleep3, int exhaust1, int exhaust2,
			int exhaust3, int flaming, int water, int freeze, int thunder,
			int dragon, int slicing, int wyvern, int demon, int armor, int tranq)
	{
		type = "heavybowgun";
		this.name = name;
		this.rarity = rarity;
		
		this.attack = attack;
		this.affinity = affinity;
		this.level1Gem = level1Gem;
		this.level2Gem = level2Gem;
		this.level3Gem = level3Gem;
		this.defenseBonus = defenseBonus;
		this.deviation = deviation;
		this.modSlots = modSlots;
		this.specialAmmo = specialAmmo;
		this.normal1 = normal1;
		this.normal2 = normal2;
		this.normal3 = normal3;
		this.piercing1 = piercing1;
		this.piercing2 = piercing2;
		this.piercing3 = piercing3;
		this.spread1 = spread1;
		this.spread2 = spread2;
		this.spread3 = spread3;
		this.sticky1 = sticky1;
		this.sticky2 = sticky2;
		this.sticky3 = sticky3;
		this.cluster1 = cluster1;
		this.cluster2 = cluster2;
		this.cluster3 = cluster3;
		this.recover1 = recover1;
		this.recover2 = recover2;
		this.recover3 = recover3;
		this.poison1 = poison1;
		this.poison2 = poison2;
		this.poison3 = poison3;
		this.paralysis1 = paralysis1;
		this.paralysis2 = paralysis2;
		this.paralysis3 = paralysis3;
		this.sleep1 = sleep1;
		this.sleep2 = sleep2;
		this.sleep3 = sleep3;
		this.exhaust1 = exhaust1;
		this.exhaust2 = exhaust2;
		this.exhaust3 = exhaust3;
		this.flaming = flaming;
		this.water = water;
		this.freeze = freeze;
		this.thunder = thunder;
		this.dragon = dragon;
		this.slicing = slicing;
		this.wyvern = wyvern;
		this.demon = demon;
		this.armor = armor;
		this.tranq = tranq;
	}

	@Override
	public String getAllInfo()
	{
		allInfo = 
				"Rarity: " + rarity + "\n\n"
				
				+ "Attack: " + attack + "\n"
				+ "Affinity: " + affinity + "%\n\n"
				
				+ "Deviation: " + deviation + "\n"
				+ "Mod slots: " + modSlots + "\n"
				+ "Special ammo: " + specialAmmo + "\n\n"
				
				// Should use a gridpane... this is the price
				// I pay for using inheritance...
				+ "Normal: " + normal1 + " - " + normal2 + " - " + normal3 + "\tPiercing: " + piercing1 + " - " + piercing2 + " - " + piercing3 + "\n"
				+ "Spread: " + spread1 + " - " + spread2 + " - " + spread3 + "\tSticky: " + sticky1 + " - " + sticky2 + " - " + sticky3 + "\n"
				+ "Cluster: " + cluster1 + " - " + cluster2 + " - " + cluster3 + "\tRecover: " + recover1 + " - " + recover2 + " - " + recover3 + "\n"
				+ "Poison: " + poison1 + " - " + poison2 + " - " + poison3 + "\tParalysis: " + paralysis1 + " - " + paralysis2 + " - " + paralysis3 + "\n"
				+ "Sleep: " + sleep1 + " - " + sleep2 + " - " + sleep3 + "\t\tExhaust: " + exhaust1 + " - " + exhaust2 + " - " + exhaust3 + "\n\n"
				+ "Flaming: " + flaming + ", Water: " + water + ", Freeze: " + freeze + ", Thunder: " + thunder + ", Dragon: " + dragon 
				+ ", Slicing: " + slicing + ", Wyvern: " + wyvern + ", Demon: " + demon + ", Armor: " + armor + ", Tranq: " + tranq + "\n\n"

				
				+ "Level 1 gem slots: " + level1Gem + "\n"
				+ "Level 2 gem slots: " + level2Gem + "\n"
				+ "Level 3 gem slots: " + level3Gem + "\n\n"
				
				+ "Defense bonus: " + defenseBonus + "\n\n";
		
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

	public String getDeviation() {
		return deviation;
	}

	public int getModSlots() {
		return modSlots;
	}

	public String getSpecialAmmo() {
		return specialAmmo;
	}

	public int getNormal1() {
		return normal1;
	}

	public int getNormal2() {
		return normal2;
	}

	public int getNormal3() {
		return normal3;
	}

	public int getPiercing1() {
		return piercing1;
	}

	public int getPiercing2() {
		return piercing2;
	}

	public int getPiercing3() {
		return piercing3;
	}

	public int getSpread1() {
		return spread1;
	}

	public int getSpread2() {
		return spread2;
	}

	public int getSpread3() {
		return spread3;
	}
	
	public int getSticky1() {
		return sticky1;
	}

	public int getSticky2() {
		return sticky2;
	}

	public int getSticky3() {
		return sticky3;
	}

	public int getCluster1() {
		return cluster1;
	}

	public int getCluster2() {
		return cluster2;
	}

	public int getCluster3() {
		return cluster3;
	}

	public int getRecover1() {
		return recover1;
	}

	public int getRecover2() {
		return recover2;
	}

	public int getRecover3() {
		return recover3;
	}

	public int getPoison1() {
		return poison1;
	}

	public int getPoison2() {
		return poison2;
	}

	public int getPoison3() {
		return poison3;
	}

	public int getParalysis1() {
		return paralysis1;
	}

	public int getParalysis2() {
		return paralysis2;
	}

	public int getParalysis3() {
		return paralysis3;
	}

	public int getSleep1() {
		return sleep1;
	}

	public int getSleep2() {
		return sleep2;
	}

	public int getSleep3() {
		return sleep3;
	}

	public int getExhaust1() {
		return exhaust1;
	}

	public int getExhaust2() {
		return exhaust2;
	}

	public int getExhaust3() {
		return exhaust3;
	}

	public int getFlaming() {
		return flaming;
	}

	public int getWater() {
		return water;
	}

	public int getFreeze() {
		return freeze;
	}

	public int getThunder() {
		return thunder;
	}

	public int getDragon() {
		return dragon;
	}

	public int getSlicing() {
		return slicing;
	}

	public int getWyvern() {
		return wyvern;
	}

	public int getDemon() {
		return demon;
	}

	public int getArmor() {
		return armor;
	}

	public int getTranq() {
		return tranq;
	}
}