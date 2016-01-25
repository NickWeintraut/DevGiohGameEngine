package base;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//GameState stores the current game state for access at a later time.
public class GameState {

	private HashSet<CardZoneState> cardZones;
	
	private HashSet<GameValueState> gameValues;
	
	private Phase currentPhase;
	
	private int updateID;
	
	public GameState(HashSet<CardZoneState> cardZones, HashSet<GameValueState> gameValues, Phase currentPhase, int updateID) 
	{
		this.cardZones = cardZones;
		this.gameValues = gameValues;
		this.currentPhase = currentPhase;
		this.updateID = updateID;
	}

	public Set<CardZoneState> getStoredCardZones()
	{
		return cardZones;
	}
	
	public HashSet<Card> getAllCardsCopy()
	{
		HashSet<Card> cards = new HashSet<Card>();
		for(CardZoneState zoneState : cardZones)
		{
			cards.addAll(zoneState.getCards());
		}
		return cards;
	}
	
	public HashSet<GameValueState> getStoredValues()
	{
		return gameValues;
	}
	
	public GameValueState findGameValue(GameValue gameVal)
	{
		GameValueState gameValState = null;
		Iterator<GameValueState> valIt = gameValues.iterator();
		while(gameValState == null && valIt.hasNext())
		{
			GameValueState testState = valIt.next();
			if(testState.getReference().equals(gameVal))
				gameValState = testState;
		}
		
		return gameValState;
	}
	
	/*
	public HashSet<Card> getCardsInZone(CardZone zone)
	{
		return cardsInZones.get(zone);
	}
	*/
	public CardZoneState getMatchingCardZoneState(CardZoneState state)
	{
		CardZoneState cardZoneState = null;
		Iterator<CardZoneState> zoneIt = cardZones.iterator();
		while(cardZoneState == null && zoneIt.hasNext())
		{
			CardZoneState testState = zoneIt.next();
			if(testState.getReference().equals(state.getReference()))
				cardZoneState = testState;
		}
		
		return cardZoneState;
	}
	

	public CardZoneState getStoredZoneOfCard(Card card)
	{
		CardZoneState foundZone = null;
		Iterator<CardZoneState> zoneIt = cardZones.iterator();
		while(zoneIt.hasNext() && foundZone == null)
		{
			CardZoneState currentZone = zoneIt.next();
			if(currentZone.hasCard(card))
				foundZone = currentZone;
		}
		if(foundZone != null)
			return foundZone;
		else
			return null;
	}
	
	public HashSet<GameValueState> getAllGameValueStatesCopy()
	{
		HashSet<GameValueState> gameVals = new HashSet<GameValueState>(gameValues);
		return gameVals;
	}
	
	public GameValueState getMatchingGameValueState(GameValueState gameValState)
	{
		GameValueState foundValueState = null;
		Iterator<GameValueState> valIt = gameValues.iterator();
		while(foundValueState == null && valIt.hasNext())
		{
			GameValueState currentState = valIt.next();
			if(currentState.getReferenceEquals(gameValState))
				foundValueState = currentState;
		}
		
		return foundValueState;
	}
	
	public int getUpdateID()
	{
		return updateID;
	}
	
	public Phase getCurrentPhase()
	{
		return currentPhase;
	}

}
