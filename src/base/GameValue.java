package base;

/**
 * Game values are values that are changed throughout the game and require tracking, primarily for State Change Checking
 * @author Nick
 *
 */
public class GameValue {
	
	private int value;
	private String name;

	public GameValue(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
}
