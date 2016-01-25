package dginput;

import java.util.LinkedList;
import java.util.List;

import dgcards.CreatureCard;
import base.GameObject;
import base.InputRequest;
import base.InvalidSelectionException;
import base.Player;
import base.SelectionInputRequest;

public class CreatureSelectionRequest extends InputRequest {

	private SelectionInputRequest selectionRequest;
	
	public CreatureSelectionRequest(String message, Player owner, List<CreatureCard> possibleSelections, int min, int max) {
		super(message, owner);
		selectionRequest = new SelectionInputRequest(message, owner, creaturesToGameObjects(possibleSelections), min, max);
	}
	
	private static List<GameObject> creaturesToGameObjects(List<CreatureCard> creatures)
	{
		List<GameObject> gameObjects = new LinkedList<GameObject>();
		gameObjects.addAll(creatures);
		return gameObjects;
	}
	
	private static List<CreatureCard> gameObjectsToCreatures(List<GameObject> gameObjects)
	{
		List<CreatureCard> creatures = new LinkedList<CreatureCard>();
		for(GameObject gameObject : gameObjects)
		{
			creatures.add((CreatureCard) gameObject);
		}
		return creatures;
	}
	
	public List<CreatureCard> getPossibleCreatures()
	{
		return gameObjectsToCreatures(selectionRequest.getPossibleSelections());
	}
	
	public void setCreatures(List<CreatureCard> creatures) throws InvalidSelectionException
	{
		selectionRequest.setSelections(creaturesToGameObjects(creatures));
	}
	
	public List<CreatureCard> getSelectedCreatures()
	{
		return gameObjectsToCreatures(selectionRequest.getSelections());
	}
	
	public boolean hasAnswer()
	{
		return selectionRequest.hasAnswer();
	}

}
