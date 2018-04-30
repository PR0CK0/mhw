package cs317.project.mhw.item;

/**
 * 
 * @author Tyler Procko
 * @date January-May 2018
 * 
 * Item object class. Stores all data for an instance of the class.
 *
 */

public class Item 
{
	private String name;
	private int capacity;
	private String description;
	
	/**
	 * Default constructor.
	 * 
	 * @param name
	 * @param capacity
	 * @param description
	 */
	public Item(String name, int capacity, String description)
	{
		this.name = name;
		this.capacity = capacity;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getDescription() {
		return description;
	}
}
