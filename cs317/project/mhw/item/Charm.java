package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Charm object class. Stores all data for an instance of the class.
 *
 */

public class Charm 
{
	private String name;
	private String skill1;
	private int skill1Level;
	private String skill2;
	private int skill2Level;
	
	/**
	 * Dummy constructor for ArmorBuilder class.
	 */
	public Charm(){}
	
	/**
	 * Default constructor. 
	 * 
	 * @param name
	 * @param skill1
	 * @param skill1Level
	 * @param skill2
	 * @param skill2Level
	 */
	public Charm(String name, String skill1, int skill1Level, String skill2, int skill2Level)
	{
		this.name = name;
		this.skill1 = skill1;
		this.skill1Level = skill1Level;
		this.skill2 = skill2;
		this.skill2Level = skill2Level;
	}

	public String getName() {
		return name;
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
}
