package base;

//means a thing can update other things
public interface Updater {

	public void updateUpdatables();
	
	public void addUpdatable(Updatable updatable);
	
	public void removeUpdatable(Updatable updatable);
}
