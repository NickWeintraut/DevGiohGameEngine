package base;

import java.util.LinkedList;
import java.util.List;

import base.GameObject;
import base.InputRequest;
import base.InvalidSelectionException;
import base.Player;
import base.SelectionInputRequest;

//an input request, oh gosh there are so many classes
public class PlayerSelectionRequest extends InputRequest {

	private SelectionInputRequest selectionRequest;
	
	public PlayerSelectionRequest(String message, Player owner, List<Player> possibleSelections, int min, int max) {
		super(message, owner);
		selectionRequest = new SelectionInputRequest(message, owner, playersToGameObjects(possibleSelections), min, max);
	}
	
	private static List<GameObject> playersToGameObjects(List<Player> players)
	{
		List<GameObject> gameObjects = new LinkedList<GameObject>();
		gameObjects.addAll(players);
		return gameObjects;
	}
	
	private static List<Player> gameObjectsToPlayers(List<GameObject> gameObjects)
	{
		List<Player> players = new LinkedList<Player>();
		for(GameObject gameObject : gameObjects)
		{
			players.add((Player) gameObject);
		}
		return players;
	}
	
	public List<Player> getPossiblePlayers()
	{
		return gameObjectsToPlayers(selectionRequest.getPossibleSelections());
	}
	
	public void setSelectedPlayers(List<Player> players) throws InvalidSelectionException
	{
		selectionRequest.setSelections(playersToGameObjects(players));
	}
	
	public List<Player> getSelectedPlayers()
	{
		return gameObjectsToPlayers(selectionRequest.getSelections());
	}
	
	public boolean hasAnswer()
	{
		return selectionRequest.hasAnswer();
	}

}
