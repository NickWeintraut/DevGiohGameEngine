package base;

import java.util.LinkedList;
import java.util.List;

import base.GameObject;
import base.InputRequest;
import base.InvalidSelectionException;
import base.Player;
import base.SelectionInputRequest;

/*
 * Input request that wants a number of cards from a given set, and specifies the valid quantities of cards to select
 */
public class CardSelectionRequest extends InputRequest {

	private SelectionInputRequest selectionRequest;
	
	public CardSelectionRequest(String message, Player owner, List<Card> possibleSelections, int min, int max) {
		super(message, owner);
		selectionRequest = new SelectionInputRequest(message, owner, cardsToGameObjects(possibleSelections), min, max);
	}
	
	private static List<GameObject> cardsToGameObjects(List<Card> cards)
	{
		List<GameObject> gameObjects = new LinkedList<GameObject>();
		gameObjects.addAll(cards);
		return gameObjects;
	}
	
	private static List<Card> gameObjectsToCards(List<GameObject> gameObjects)
	{
		List<Card> cards = new LinkedList<Card>();
		for(GameObject gameObject : gameObjects)
		{
			cards.add((Card) gameObject);
		}
		
		return cards;
	}
	
	public List<Card> getPossibleCards()
	{
		return gameObjectsToCards(selectionRequest.getPossibleSelections());
	}
	
	public void setSelectedCards(List<Card> cards) throws InvalidSelectionException
	{
		selectionRequest.setSelections(cardsToGameObjects(cards));
	}
	
	public List<Card> getSelectedCards()
	{
		return gameObjectsToCards(selectionRequest.getSelections());
	}
	
	public boolean hasAnswer()
	{
		return selectionRequest.hasAnswer();
	}

}
