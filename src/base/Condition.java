package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * checks conditions every time it updates and publishes results to a boolean variable which can be accessed with a getter
 */
abstract public class Condition extends GameObject implements Updatable, Serializable{
	
	static final long serialVersionUID = 514;
	
	protected boolean isConditionMet;
	
	public Condition()
	{
		super();
	}

	public boolean isConditionMet()
	{
		if(isConditionMet)
			return true;
		else
			return false;
	}
	
	abstract protected boolean checkCondition();
	
	public void update()
	{
		if(checkCondition())
			isConditionMet = true;
		else
			isConditionMet= false;
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
	
	public static Condition deserialize(String filename)
	{
		System.out.println("deserialize");
		
		try(FileInputStream fIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fIn))
				{ Condition newSet = (Condition) in.readObject();
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
