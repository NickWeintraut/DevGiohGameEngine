package base;

import java.util.LinkedList;
import java.util.List;

/*
 * i love this class, it handles retrieving the next player to get a turn and accounts for player death
 */
public class PlayerOrder extends UpdaterGameObject implements Updatable{

	LinkedList<Player> players;
	Player currentPlayer;
	
	public PlayerOrder(LinkedList<Player> players) {
		super();
		this.players = players;
		for(Player player : players)
		{
			addUpdatable(player);
		}
		addPlayersToChildren();
		nextPlayer();
	}

	public PlayerOrder(GameObject parent, LinkedList<Player> players) {
		super(parent);
		this.players = players;
		for(Player player : players)
		{
			addUpdatable(player);
		}
		addPlayersToChildren();
		nextPlayer();
	}
	
	public void addPlayersToChildren()
	{
		for(Player player : players)
		{
			addChild(player);
		}
	}
	
	public List<Player> getPlayers()
	{
		return players;
	}
	
	public void synchAlivePlayers()
	{
		for(Player player : new LinkedList<Player>(players))
		{
			if(!player.isAlive())
			{
				players.remove(player);
				Engine.getCurrentTurnManager().exitCurrentTurn();
			}
		}
	}
	
	public List<Player> getOpposingPlayers()
	{
		List<Player> opposingPlayers = new LinkedList<Player>(players);
		opposingPlayers.remove(getCurrentPlayer());
		return opposingPlayers;
	}
	
	public void update()
	{
		updateUpdatables();
	}
	
	public Player getCurrentPlayer()
	{
		if(currentPlayer == null)
		{
			nextPlayer();
			return currentPlayer;
		}
		else
			return currentPlayer;
	}
	
	public void nextPlayer()
	{
		currentPlayer = getNextOrderPlayer();
	}
	
	protected Player getNextOrderPlayer()
	{
		if(currentPlayer == null || currentPlayer == players.getLast())
			return players.getFirst();
		else
				return players.get(players.indexOf(currentPlayer) + 1);
	}

}
