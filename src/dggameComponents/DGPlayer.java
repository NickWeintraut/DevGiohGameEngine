package dggameComponents;

import java.util.LinkedList;

import damage.Damagable;
import damage.DamageSource;
import dgcardZones.*;
import dgconditions.PlayCreatureCondition;
import dgconditions.PlayEnhancementCondition;
import base.Card;
import base.Condition;
import base.GameValue;
import base.Player;

public class DGPlayer extends Player implements Damagable{
	
	/*shuffle cards (List arraylist)
	 * draw cards
	 * search library
	 * move card to zone (to a different zone)
	 * ex, move from deck to hand, field to graveyard, etc.
	 * 
	 * life points
	 * 
	 * update method implements updateable
	 * 	goes through all the zones and updates them.
	 * 
	 * */
	private final int STARTING_LIFE_POINTS = 400;
	private final int STARTING_HAND_SIZE = 5;
	
	private Deck deck;  //change later these are all hash maps
	private Hand hand;  //change later
	private DGCardZone graveyard;
	private DGCardZone field;
	
	private GameValue lifePoints;
	
	private PlayCreatureCondition creatureCondition;
	
	private PlayEnhancementCondition enhancementCondition;
	
	public DGPlayer(String name, LinkedList<Card> cardsInDeck)
	{
		super(name);
		
		creatureCondition = new PlayCreatureCondition();
		addUpdatable(creatureCondition);
		enhancementCondition = new PlayEnhancementCondition(this);
		addUpdatable(enhancementCondition);
		
		deck = new Deck(cardsInDeck, this);
		addCardZone(deck);
		hand = new Hand(this);
		addCardZone(hand);
		graveyard = new DGCardZone(this);
		addCardZone(graveyard);
		field = new DGCardZone(this);
		addCardZone(field);
		
		lifePoints = new GameValue((getName() + " life"), STARTING_LIFE_POINTS);
		addPlayerValue(lifePoints);
		
	}
	
	public void drawCard() //draws a card from deck and places it into hand
	{
		hand.addCard(deck.draw());
	}
	
	public void drawMultiple(int amount)
	{
		int i = 0;
		while(!deck.isEmpty() && i < amount)
		{
			drawCard();
			i++;
		}
	}

	public void mulligan()
	{
		int size = hand.getSize();
		if(size == 0)
		{
			hand.addCards(deck.drawMultiple(STARTING_HAND_SIZE));
		}
		else
		{
			deck.addCards(hand.removeAllCards());
			deck.shuffle();
			drawMultiple(size - 1);
		}
	}
	
	public void shuffleDeck()
	{
		deck.shuffle();
	}

	public Hand getHand() 
	{
		return hand;
	}
	
	public Deck getDeck()
	{
		return deck;
	}
	
	public DGCardZone getGraveyard()
	{
		return graveyard;
	}
	
	public DGCardZone getField()
	{
		return field;
	}
	
	public int getRemainingLifePoints()
	{
		return lifePoints.getValue();
	}
	
	public void takeDamage(int amount, DamageSource damage)
	{
		lifePoints.setValue(lifePoints.getValue() - amount);
		if(lifePoints.getValue() <= 0)
		{
			this.destroy();
		}
	}
	
	public Condition getPlayCreatureCondition()
	{
		return creatureCondition;
	}
	
	public PlayEnhancementCondition getPlayEnhancementCondition()
	{
		return enhancementCondition;
	}

}
