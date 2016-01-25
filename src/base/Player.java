package base;

import java.util.LinkedList;
import java.util.List;

//basic player class, there are so many classes so forgive me that im not doing in depth commentary
//the interfaces should help understand, this is basically just a data storage class
public abstract class Player extends UpdaterGameObject implements Destroyable, Updatable, ZoneContainer, ValueContainer{

	static final long serialVersionUID = 32;
	
	private String name;
	
	protected boolean isAlive;
	
	private List<CardZone> cardZones;
	private List<GameValue> playerValues;
	
	public Player(String name){
		super();
		isAlive = true;
		this.cardZones = new LinkedList<CardZone>();
		this.playerValues = new LinkedList<GameValue>();
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addCardZone(CardZone zone)
	{
		cardZones.add(zone);
		addUpdatable(zone);
	}
	
	public void addPlayerValue(GameValue value)
	{
		playerValues.add(value);
	}
	
	public List<CardZone> getCardZones()
	{
		return cardZones;
	}
	
	public List<GameValue> getGameValues()
	{
		return playerValues;
	}
	
	public void update()
	{
		//System.out.println("updating");
		updateUpdatables();
	}
	
	public boolean isAlive()
	{
		return isAlive;
	}
	
	public void destroy()
	{
		isAlive = false;
	}

}
