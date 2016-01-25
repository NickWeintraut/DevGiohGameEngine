package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//provides data access to objects in game and the game itself to see children and parents
abstract public class GameObject implements Serializable {
	
	static final long serialVersionUID = 24;
	
	protected GameObject parent;
	
	protected List<GameObject> children;

	public GameObject() {
		this.children = new LinkedList<GameObject>();
	}
	
	public GameObject(GameObject parent)
	{
		this();
		this.parent = parent;
	}
	
	public void addChild(GameObject child)
	{
		children.add(child);
		child.setParent(this);
	}
	
	public void setParent(GameObject parent)
	{
		this.parent = parent;
	}
	
	public boolean hasParent()
	{
		if(parent != null)
			return true;
		else
			return false;
	}
	
	public GameObject getParent()
	{
		return parent;
	}
	
	public boolean hasChildren()
	{
		if(children != null && children.size() > 0)
			return true;
		else
			return false;
	}
	
	public List<GameObject> getChildren()
	{
		return new LinkedList<GameObject>(children);
	}
	
	public HashSet<GameValue> getRecursiveGameValues()
	{
		HashSet<GameValue> recursiveValues = new HashSet<GameValue>();
		if(this instanceof ValueContainer)
		{
			recursiveValues.addAll(((ValueContainer) this).getGameValues());
		}
		for(GameObject child : children)
		{
			recursiveValues.addAll(child.getRecursiveGameValues());
		}
		return recursiveValues;
	}
	
	public HashSet<CardZone> getRecursiveCardZones()
	{
		HashSet<CardZone> recursiveValues = new HashSet<CardZone>();
		if(this instanceof ZoneContainer)
		{
			recursiveValues.addAll(((ZoneContainer) this).getCardZones());
		}
		for(GameObject child : children)
		{
			recursiveValues.addAll(child.getRecursiveCardZones());
		}
		return recursiveValues;
	}
	
	public boolean serialize(String filename)
	{
		try(FileOutputStream fOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fOut))
				{
			out.writeObject(this);//send in a statistic
			return true;
				}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static GameObject deserialize(String filename)
	{
		System.out.println("deserialize");
		
		try(FileInputStream fIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fIn))
				{ GameObject newSet = (GameObject) in.readObject();
				return newSet;
				}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
