package base;

//similar to CardZoneState, but for gamevalues
public class GameValueState extends GameValue implements StateStorage {
	
	private GameValue reference;

	public GameValueState(GameValue reference) {
		super(reference.getName(), reference.getValue());
		this.reference = reference;
	}
	
	public void refresh()
	{
		setValue(reference.getValue());
	}
	
	public GameValue getReference()
	{
		return reference;
	}
	
	public boolean getReferenceEquals(StateStorage stateStore)
	{
		if(this.getReference().equals(stateStore.getReference()))
			return true;
		else
			return false;	
	}

}
