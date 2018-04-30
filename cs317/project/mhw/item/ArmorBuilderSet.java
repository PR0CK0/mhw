package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * ArmorBuilderSet object class. Stores all data for an instance of the class.
 *
 */

public class ArmorBuilderSet 
{
	private String setName;
	private String weaponName;
	private String headName;
	private String chestName;
	private String armsName;
	private String waistName;
	private String legsName;
	private String charmName;
	
	/**
	 * Default constructor.
	 * 
	 * @param setName
	 * @param weaponName
	 * @param headName
	 * @param chestName
	 * @param armsName
	 * @param waistName
	 * @param legsName
	 * @param charmName
	 */
	public ArmorBuilderSet(String setName, String weaponName, String headName, String chestName,
			String armsName, String waistName, String legsName, String charmName)
	{
		this.setName = setName;
		this.weaponName = weaponName;
		this.headName = headName;
		this.chestName = chestName;
		this.armsName = armsName;
		this.waistName = waistName;
		this.legsName = legsName;
		this.charmName = charmName;
	}

	public String getSetName() {
		return setName;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public String getHeadName() {
		return headName;
	}

	public String getChestName() {
		return chestName;
	}

	public String getArmsName() {
		return armsName;
	}

	public String getWaistName() {
		return waistName;
	}

	public String getLegsName() {
		return legsName;
	}

	public String getCharmName() {
		return charmName;
	}
}
