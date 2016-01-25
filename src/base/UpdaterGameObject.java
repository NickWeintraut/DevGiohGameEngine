package base;

import java.util.LinkedList;

//gameobject that implements updater's methods
abstract public class UpdaterGameObject extends GameObject implements Updater {

	protected LinkedList<Updatable> updatables;
	
	public UpdaterGameObject() {
		updatables = new LinkedList<Updatable>();
	}
	
	public UpdaterGameObject(GameObject parent)
	{
		super(parent);
		updatables = new LinkedList<Updatable>();
	}
	
	public void addUpdatable(Updatable updatable)
	{
		updatables.add(updatable);
	}
	
	public void removeUpdatable(Updatable updatable)
	{
		updatables.remove(updatable);
	}
	
	public void updateUpdatables()
	{
		for(Updatable updatable : updatables)
		{
			updatable.update();
		}
	}

}
