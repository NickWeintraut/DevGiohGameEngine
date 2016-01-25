package base;

//marks that a class stores a state and requires methods to do so

public interface StateStorage {
	
	public Object getReference();
	
	public void refresh();
	
	public boolean getReferenceEquals(StateStorage stateStore);

}
