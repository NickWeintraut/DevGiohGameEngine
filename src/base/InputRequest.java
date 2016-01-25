package base;

//these store information about a request for input, and are sent to the game output to be processed and returned
abstract public class InputRequest implements Ownable{
	
	String message;
	Player owner;
	
	public InputRequest(String message, Player owner) {
		this.message = message;
		this.owner = owner;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public Player getOwner()
	{
		return owner;
	}
	abstract public boolean hasAnswer();

}
