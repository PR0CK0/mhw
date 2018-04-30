package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Monster object class. Stores all data for an instance of the class.
 *
 */

public class Monster 
{
	private String name;
	private String type;
	private int poisonWeakness; 
	private int sleepWeakness;
	private int paralysisWeakness;
	private int blastWeakness;
	private int stunWeakness;
	
	/**
	 * Default constructor.
	 * 
	 * @param name
	 * @param type
	 * @param poisonWeakness
	 * @param sleepWeakness
	 * @param paralysisWeakness
	 * @param blastWeakness
	 * @param stunWeakness
	 */
	public Monster(String name, String type, int poisonWeakness, int sleepWeakness, int paralysisWeakness,
			int blastWeakness, int stunWeakness)
	{
		this.name = name;
		this.type = type;
		this.poisonWeakness = poisonWeakness;
		this.sleepWeakness = sleepWeakness;
		this.paralysisWeakness = paralysisWeakness;
		this.blastWeakness = blastWeakness;
		this.stunWeakness = stunWeakness;
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}

	public int getPoisonWeakness() {
		return poisonWeakness;
	}
	
	public int getSleepWeakness() {
		return sleepWeakness;
	}

	public int getParalysisWeakness() {
		return paralysisWeakness;
	}

	public int getBlastWeakness() {
		return blastWeakness;
	}

	public int getStunWeakness() {
		return stunWeakness;
	}
}
