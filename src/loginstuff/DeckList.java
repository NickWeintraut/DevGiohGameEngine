package loginstuff;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

import base.Card;

public class DeckList implements Serializable
{
	static final long serialVersionUID = 5678;
	
	private LinkedList<Card> cards;
	private String name;
	
	public DeckList(String name) 
	{
		this.name = name;
		cards = new LinkedList<Card>();
	}
	
	public String toString()
	{
		return this.name;
	}
	
	public LinkedList<Card> getCards()
	{
		return cards;
	}
	
	public void addCard(Card card)
	{
		cards.add(card);
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
	
	public static DeckList deserialize(String filename)
	{
		System.out.println("deserialize");
		
		try(FileInputStream fIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fIn))
				{ DeckList newSet = (DeckList) in.readObject();
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
