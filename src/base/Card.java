package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/*
 * Generic card class sets the framework for cards in card games
 */
public abstract class Card extends UpdaterGameObject implements Destroyable, Updatable, Ownable, Serializable {
	
	static final long serialVersionUID = 1;
	
	private String name;
	protected CardZone zone;
	protected ArrayList<Effect> effects;
	protected Player owner;
	
	public Card(String name) {
		super();
		effects = new ArrayList<Effect>();
		this.name = name;
		zone = null;
	}
	
	public Card()
	{
		super();
		effects = new ArrayList<Effect>();
		zone = null;
	}
	
	public String getName() {
		return name;
	}
	
	protected abstract Condition findPlayCondition();
	
	public void addEffect(Effect e) {
		effects.add(e);
		if(e instanceof Updatable)
		{
			addUpdatable((Updatable) e);
		}
	}
	
	public void addEffect(List<Effect> effects)
	{
		for(Effect effect : effects)
		{
			addEffect(effect);
		}
	}
	
	public void removeEffect(Effect e) {
		if(effects.contains(e)){
			effects.remove(e);
			e.deactivate();
			if(e instanceof Updatable)
			{
				removeUpdatable((Updatable) e);
			}
		}
	}
	
	public List<Effect> getEffects()
	{
		return effects;
	}
	
	public void setCurrentZone(CardZone zone)
	{
		this.zone = zone;
	}
	
	public CardZone getCurrentZone()
	{
		return zone;
	}
	
	public abstract void destroy();
	
	public Effect getEffect(int effectIndex) {
		return effects.get(effectIndex);
	}
	
	public boolean isPlayable(){
		
		return findPlayCondition().isConditionMet();
	}
	
	public void update()
	{
		/*if(effects.size() == 1)
		{
			System.out.println(effects.get(0).getClass().getSimpleName());
		}*/
		if(!updatables.containsAll(effects));
		{
			//System.out.println(updatables.size());
			updatables.removeAll(effects);
			updatables.addAll(effects);
		}
		updateUpdatables();
	}
	
	public Player getOwner()
	{
		return owner;
	}
	
	public void setOwner(Player owner)
	{
		this.owner = owner;
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
	
	public static Card deserialize(String filename)
	{
		System.out.println("deserialize");
		
		try(FileInputStream fIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fIn))
				{ Card newSet = (Card) in.readObject();
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
