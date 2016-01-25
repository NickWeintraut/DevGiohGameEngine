package base;


import java.util.*;

/**
 * The abstract base class that all collections of Cards are based on.
 * Uses a LinkedList because cards are VERY often drawn from the first slot in the collection
 * 
 * @author Eric M. Lynn!
 * @author Nick Weintraut
 * (Nick Weintraut just implemented Updatable and made refactoring changes)
 */
public abstract class CardZone extends UpdaterGameObject implements Updatable, Ownable{
	
	static final long serialVersionUID = 415;
	
	protected LinkedList<Card> cards;
	private Player owner;
	
	/**
	 * no-param constructor: instantiates an empty linkedlist of cards
	 */
	public CardZone(Player owner) {
		this();
		this.owner = owner;
	}
	
	public CardZone()
	{
		super();
		this.cards = new LinkedList<Card>();
	}
	
	public CardZone(LinkedList<Card> cards)
	{
		this();
		setCards(cards);
	}
	
	
	/**
	 * 1-param constructor: Sets a given linkedlist equal to the CardZone's instance
	 * of LinkedList
	 * @param cards the linkedlist of cards for the zone to be based on
	 */
	public CardZone(LinkedList<Card> cards, Player owner){
		this(owner);
		setCards(cards);
	}
	
	/**
	 * Gets the linkedlist of Cards the zone contains
	 * @returns the zone's linkedlist of cards
	 */
	public LinkedList<Card> getCards()
	{
		return cards;
	}
	
	public void setCards(LinkedList<Card> cards)
	{
		updatables.removeAll(cards);
		addCards(cards);
	}
	
	/**
	 * Adds a card to the CardZone
	 * @param c the card to be added
	 */
	public void addCard(Card c) {
		if(!cards.contains(c))
		{
			cards.add(c);
			addUpdatable(c);
			if(c.getOwner() == null)
			{
				c.setOwner(getOwner());
			}
			c.setCurrentZone(this);
		}
	}
	
	/**
	 * removes the given card from the collection
	 * @param c the card to be removed
	 */
	public void removeCard(Card c){
		if(cards.contains(c))
		{
			cards.remove(c);
			removeUpdatable(c);
			c.setCurrentZone(null);
		}
	}
	
	/**
	 * Checks to see if the card passed in is present in the collection
	 * @param c the card to be searched for
	 */
	public boolean hasCard(Card c){
		if(cards.contains(c))
			return true;
		else
			return false;	
	}
	
	public void addCards(Collection<Card> cards)
	{
		for(Card card : cards)
		{
			addCard(card);
		}
	}
	
	public LinkedList<Card> removeAllCards()
	{
		LinkedList<Card> returnedCards = new LinkedList<Card>();
		while(!isEmpty())
		{
			returnedCards.add(cards.removeFirst());
		}
		return returnedCards;
	}
	
	public int getSize()
	{
		return cards.size();
	}
	
	/**
	 * if the CardZone has no cards in it, return true, else, return false.
	 */
	public boolean isEmpty(){
		if(cards.size() == 0)
			return true;
		else
			return false;
	}
	
	static public void moveCard(Card card, CardZone beginningZone, CardZone endingZone)
	{
		if(beginningZone != null)
		{
			beginningZone.removeCard(card);
		}
		if(endingZone != null)
		{
			endingZone.addCard(card);
		}
	}
	
	public Player getOwner()
	{
		return owner;
	}
	
	/**
	 * updates all the cards in the card zone
	 */
	public void update()
	{
		updateUpdatables();
	}
}
