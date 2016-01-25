package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * Effects do things to the game, and know their source, owner, and name
 */
abstract public class Effect extends UpdaterGameObject implements Activatable, Updatable, Ownable, Serializable {

	static final long serialVersionUID = 1022;
	
	protected Player owner;
	protected Card source;
	protected boolean isActive;
	private String name;
	
	public Effect(Card source, String name) {
		super();
		isActive = false;
		this.name = name;
		this.source = source;
		this.owner = source.getOwner();
	}

	public Card getSource()
	{
		return this.source;
	}
	
	public Player getOwner()
	{
		return this.owner;
	}
	
	abstract public void activate();
	
	abstract public void deactivate();
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public boolean serialize(String filename)
	{
		try(FileOutputStream fOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fOut))
				{
			out.writeObject(this); //write this DeckList to a file
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
	
	public static Effect deserialize(String filename)
	{
		System.out.println("deserialize");
		
		try(FileInputStream fIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fIn))
				{ Effect newSet = (Effect) in.readObject();
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
	
	public abstract void update();

}
