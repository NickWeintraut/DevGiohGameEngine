package base;

import java.util.LinkedList;


//using a decorator pattern to create a cardZone that archives the information of a CardZone
//useful for gamestates, as you can access information about the previous state of the cardzone in the same way as a normal cardzone
public class CardZoneState extends CardZone implements StateStorage {
	
	static final long serialVersionUID = 618;
	
	CardZone reference;

	public CardZoneState(CardZone reference) {
		this.reference = reference;
		refresh();
	}
	
	public CardZone getReference()
	{
		return reference;
	}
	
	public void refresh()
	{
		this.cards = new LinkedList<Card>(reference.getCards());
	}
	
	public boolean getReferenceEquals(StateStorage state)
	{
		if(reference.equals(state.getReference()))
			return true;
		else
			return false;
	}
	
	public void update()
	{
		refresh();
	}
}
