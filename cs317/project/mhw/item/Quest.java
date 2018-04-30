package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Quest object class. Stores all data for an instance of the class.
 *
 */

public class Quest 
{
	private String name;
	private int level;
	private String targetMonster;
	private int hr;
	private int rewardMoney;
	
	/**
	 * Default constructor.
	 * 
	 * @param name
	 * @param level
	 * @param targetMonster
	 * @param hr
	 * @param rewardMoney
	 */
	public Quest(String name, int level, String targetMonster, int hr, int rewardMoney)
	{
		this.name = name;
		this.level = level; 
		this.targetMonster = targetMonster;
		this.hr = hr;
		this.rewardMoney = rewardMoney;
	}
	
	public String getName(){
		return name;
	}

	public int getLevel() {
		return level;
	}
	
	public String getTargetMonster() {
		return targetMonster;
	}
	
	public int getHr() {
		return hr;
	}

	public int getRewardMoney() {
		return rewardMoney;
	}
}
