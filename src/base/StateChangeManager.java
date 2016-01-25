package base;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//StateChangeManager handles the movements of cards across zones and changes in game values
public class StateChangeManager extends GameObject implements Updatable{
	
	
	protected HashSet<CardMovement> cardMovements;
	
	protected HashSet<GameValueChange> gameValueChanges;
	
	protected GameState oldState;
	protected GameState newState;
	
	public StateChangeManager() {
		super();
	}
	
	/**
	 * Always returns true
	 */
	public boolean isActive()
	{
		return true;
	}
	
	public void update()
	{
		if(isActive())
		{
			if(newState == null)
			{
				//System.out.println("yaaaaaas");
				newState = Engine.getCurrentGame().generateGameState();
			}

			oldState = newState;
			newState = Engine.getCurrentGame().generateGameState();
			
			synchronizeGameChanges(true);
		}
	}
	
	public HashSet<CardMovement> getCardMovement()
	{
		return cardMovements;
	}
	
	public GameState getRecentGameState()
	{
		if(newState != null)
			return newState;
		else
			return oldState;
	}
	
	//this needs checking apparently...
	public void synchronizeGameChanges(boolean overwrite)
	{
		if(overwrite || (cardMovements == null || gameValueChanges == null))
		{
			cardMovements = new HashSet<CardMovement>();
			gameValueChanges = new HashSet<GameValueChange>();
		}
		
		/*
		System.out.println(oldState.getUpdateID());
		System.out.println(newState.getUpdateID());
		System.out.println(Engine.getCurrentTopUpdateID());
		
		System.out.println(oldState.equals(newState));
		*/
		
		//generate all card movements
		HashSet<Card> oldCards = oldState.getAllCardsCopy();
	/*	System.out.println("OLD CARDS - ZONES");
		for(Card card : oldCards)
		{
			System.out.println(card.getName() + "    " + card.getCurrentZone().toString() + "    " + card.getOwner().getName());
		} */
		HashSet<Card> newCards = newState.getAllCardsCopy();
		/*System.out.println("NEW CARDS - ZONES");
		for(Card card : newCards)
		{
			System.out.println(card.getName() + "    " + card.getCurrentZone().toString() + "    " + card.getOwner().getName());
			
		} */
		
		/*
		System.out.println("oldoldoldoldoldold \n");
		
		Set<CardZoneState> oldZones = oldState.getStoredCardZones();
		for(CardZoneState zoneState : oldZones)
		{
			if(zoneState.getCards().size() > 0)
			{
				System.out.println(zoneState.getReference().getOwner().getName() + " " + zoneState.getReference().toString() + " \n");
				for(Card card : zoneState.getCards())
				{
					System.out.println(card.getName());
				}
				System.out.println(" \n \n \n");
			}
		}
		
		System.out.println("newnewnewnewnew \n ");
		
		Set<CardZoneState> newZones = newState.getStoredCardZones();
		for(CardZoneState zoneState : newZones)
		{
			if(zoneState.getCards().size() > 0)
			{
				System.out.println(zoneState.getReference().getOwner().getName() + " " + zoneState.getReference().toString() + "\n");
				for(Card card : zoneState.getCards())
				{
					System.out.println(card.getName());
				}
				System.out.println(" \n \n \n");
			}
		} 
		*/
		
		Iterator<Card> cardIt = oldCards.iterator();
		
		while(cardIt.hasNext())
		{
			Card card = cardIt.next();
			CardZoneState oldZoneState = oldState.getStoredZoneOfCard(card);
			
			//only perform operations if the card exists in the new state
			if(newCards.contains(card))
			{
				//System.out.println("woah its in new");
				CardZoneState newZoneState = newState.getStoredZoneOfCard(card);
				if(newZoneState == oldZoneState);
				{
				}
					//System.out.println("hey");
				if(oldZoneState.getReferenceEquals(newZoneState))
				{
					//no card movement will be added
					//System.out.println("ugh");
					cardIt.remove();
					newCards.remove(card);
				}
				else
				{
					//add a card movement of the card going from its referenced old zone to its referenced new zone
					//System.out.println("adding a cardMovement");
					cardMovements.add(new CardMovement(card, oldZoneState.getReference(), newZoneState.getReference()));
					newCards.remove(card);
				}
			}
			else
			{
				//this card vanished, add card movements to represent this
				cardMovements.add(new CardMovement(card, oldZoneState.getReference(), null));
			}
		}
		//if newCards still has cards in it, there are cards that came out of thin air/ an unregistered card zone
		if(newCards.size() > 0)
		{
			for(Card card: newCards)
			{
				//add a card movement that represents this card appearing out of thin air
				cardMovements.add(new CardMovement(card, null, newState.getStoredZoneOfCard(card).getReference()));
			}
		}
		
		HashSet<GameValueState> oldValStates = oldState.getAllGameValueStatesCopy();
		
		//generate all gameValue changes
		Iterator<GameValueState> valIt = oldValStates.iterator();
		while(valIt.hasNext())
		{
			GameValueState oldValState = valIt.next();
			GameValueState newValState = newState.getMatchingGameValueState(oldValState);
			if(newValState != null)
			{
				gameValueChanges.add(new GameValueChange(oldValState.getReference(), newValState.getValue() - oldValState.getValue()));
			}
		}
		
		//any gameValueStates not addressed were either created this update or deleted this update, don't worry about it
	}
	
	/**
	 * Returns the cards that moved to the given zone
	 * @param endZone
	 * @return
	 */
	public HashSet<Card> getCardsMoved(CardZone endZone)
	{
		
		HashSet<Card> cardsMoved = new HashSet<Card>();
		for(CardMovement cardMove : cardMovements)
		{
			if(cardMove.getEndZone().equals(endZone))
			{
				cardsMoved.add(cardMove.getCardMoved());
			}
		}
		return cardsMoved;
	}
	
	/**
	 * Returns the cards that moved to and from the specified end and start zones
	 * @param endZone
	 * @param beginningZone
	 * @return
	 */
	public HashSet<Card> getCardsMoved(CardZone endZone, CardZone beginningZone)
	{
		
		HashSet<Card> cardsMoved = new HashSet<Card>();
		for(CardMovement cardMove : cardMovements)
		{
			if(cardMove.getEndZone().equals(endZone) && cardMove.getBeginningZone().equals(beginningZone))
			{
				cardsMoved.add(cardMove.getCardMoved());
			}
		}
		return cardsMoved;
	}
	
	

}
