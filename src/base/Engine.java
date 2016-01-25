package base;

/**
 * Access allows classes to directly access fundamental components of the game
 * and game engine as well as stores updateID information to be passed through the update chain.
 * @author Nick
 *
 */
//still need to add some initialization exception throwing
abstract public class Engine {
	
	static private Game gameInstance = null;
	
	//keeps track of which root update this is, will be incremented by the update calls in phases
	static private int updateID = 0;
	
	//should keep this fairly low. minimum 2
	static private final int updateMod = 4;
	
	static public Game getCurrentGame()
	{
		if(gameInstance != null)
			return gameInstance;
		else
			throw(new EngineInitializationException());
	}
	
	/**
	 * uses singleton pattern in a slightly unusual way
	 * @param newInstance
	 * @return
	 */
	static public boolean setCurrentGameInstance(Game newInstance)
	{
		if(newInstance == null)
			throw(new EngineInitializationException());
		if(gameInstance == null)
		{
			gameInstance = newInstance;
			return true;
		}
		else
			return false;	
	}
	
	static public void clearCurrentGameInstance()
	{
		gameInstance = null;
	}
	
	static public ActivationStackManager getCurrentActivationStack()
	{
		ActivationStackManager activationStack = getCurrentGame().getActivationStack();
		if(activationStack == null)
			throw(new EngineInitializationException());
		return activationStack;
	}
	
	static public InputManager getCurrentInputManager()
	{
		InputManager input = getCurrentGame().getInputManager();
		if(input == null)
			throw(new EngineInitializationException());
		return input;
	}
	
	static public GameState getCurrentGameState()
	{
		GameState currentState = getCurrentGame().getGameState();
		if(currentState == null)
			throw(new EngineInitializationException());
		return currentState;
	}
	
	static public StateChangeManager getCurrentStateChangeManager()
	{
		StateChangeManager changeManager = getCurrentGame().getCurrentStateChangeManager();
		if(changeManager == null)
			throw(new EngineInitializationException());
		return changeManager;
	}
	
	static public TurnManager getCurrentTurnManager()
	{
		TurnManager turnManager = getCurrentGame().getTurnManager();
		if(turnManager == null)
			throw(new EngineInitializationException());
		return turnManager;
	}
	
	static public void nextTopUpdateID()
	{
		updateID = (updateID + 1) % updateMod;
	}
	
	static public int getCurrentTopUpdateID()
	{
		return updateID;
	}
}
