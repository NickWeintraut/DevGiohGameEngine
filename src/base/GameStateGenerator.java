package base;

import java.util.HashSet;

//generates gamestate by using recursive methods in gameobject class and calling them from game, the topmost parent gameobject
public class GameStateGenerator extends GameObject{
	
	static final long serialVersionUID = 25;
	
	public GameStateGenerator() {
		super();
	}
	
	public GameState generateGameState(Game game)
	{
		HashSet<CardZoneState> cardZones = new HashSet<CardZoneState>();
		HashSet<GameValueState> gameValues = new HashSet<GameValueState>();
		
		for(CardZone zone : game.getRecursiveCardZones())
		{
			cardZones.add(new CardZoneState(zone));
		}
		
		for(GameValue value : game.getRecursiveGameValues())
		{
			gameValues.add(new GameValueState(value));
		}
		return new GameState(cardZones, gameValues, Engine.getCurrentTurnManager().getCurrentTurn().getCurrentPhase(), Engine.getCurrentTopUpdateID());
	}
	
	public GameState generateGameState(Game game, boolean noPhase)
	{
		HashSet<CardZoneState> cardZones = new HashSet<CardZoneState>();
		HashSet<GameValueState> gameValues = new HashSet<GameValueState>();
		
		for(CardZone zone : game.getRecursiveCardZones())
		{
			cardZones.add(new CardZoneState(zone));
		}
		
		for(GameValue value : game.getRecursiveGameValues())
		{
			gameValues.add(new GameValueState(value));
		}
		return new GameState(cardZones, gameValues, null, Engine.getCurrentTopUpdateID());
	}

}
