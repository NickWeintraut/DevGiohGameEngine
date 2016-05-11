package base;

import java.util.LinkedList;

/*
 * Abstract game class sets up links between major game components using abstract methods that allow Game subclasses to specify what instances of each component to use
 */
public abstract class Game extends UpdaterGameObject implements Updatable{
	
	static final long serialVersionUID = 222;
	
	private InputManager inputManager;
	
	private PlayerOrder playerOrder;
	
	private TurnManager turnManager;
	
	private GameStateGenerator gameStateGenerator;
	
	private GameState currentState;
	
	//handles immediate state changes
	private StateChangeManager changeManager;
	
	private ActivationStackManager activationStack;

	public Game(LinkedList<Player> players, GameOutputable gameOutput) {
		super();
		Engine.setCurrentGameInstance(this);
		
		inputManager = createInputManager(gameOutput);
		playerOrder = createPlayerOrder(players);
		gameStateGenerator = createGameStateGenerator();
		turnManager = createTurnManager();
		changeManager = createStateChangeManager();
		activationStack = createActivationStackManager();
		
		gameOutput.setGame(this);
		
		addChild(playerOrder);
		
		addUpdatable(changeManager);
		addUpdatable(playerOrder);
		addUpdatable(gameOutput);
	}
	
	//games overwrite this to return their specialized inputManager (if they have one)
	protected abstract InputManager createInputManager(InputResponder gameOutput);
	//etc
	protected abstract PlayerOrder createPlayerOrder(LinkedList<Player> players);
	//etc
	protected abstract TurnManager createTurnManager();
	//you get the point, although some of these classes are abstract so the game subclass will NEED to have special subclasses of them
	protected abstract GameStateGenerator createGameStateGenerator();
	protected abstract StateChangeManager createStateChangeManager();
	protected abstract ActivationStackManager createActivationStackManager();
	
	public void start()
	{
		generateGameState();
		changeManager.update();
		turnManager.activate();
		turnManager.run();
	}
	
	public StateChangeManager getCurrentStateChangeManager()
	{
		return changeManager;
	}
	
	public void update()
	{
		updateUpdatables();
		playerOrder.synchAlivePlayers();
		if(playerOrder.getPlayers().size() == 1)
		{
			endGame(playerOrder.getNextOrderPlayer());
		}
		if(!activationStack.getIsRunning())
		{
			activationStack.runStack();
		}
	}
	
	/**
	 * throws an EndGameException containing the winning player upwards
	 * @param winningPlayer The winning Player
	 * @throws EndGameException Tells whatever made a game that the game has ended.
	 */
	public void endGame(Player winningPlayer)
	{
		turnManager.deactivate();
		inputManager.ask(new EndGameInputRequest("You won the game!", winningPlayer));
	}
	
	public InputManager getInputManager()
	{
		return inputManager;
	}
	
	public Player getCurrentPlayer()
	{
		return turnManager.getCurrentTurnPlayer();
	}
	
	public TurnManager getTurnManager()
	{
		return turnManager;
	}
	
	
	public PlayerOrder getPlayerOrder()
	{
		return playerOrder;
	}
	
	public ActivationStackManager getActivationStack()
	{
		return activationStack;
	}
	
	public GameState getGameState()
	{
		return currentState;
	}
	
	public GameState generateGameState()
	{
		//checks if first time creating gameState
		if(currentState == null)
		{
			currentState = gameStateGenerator.generateGameState(this, true);
		}
		else if(currentState.getUpdateID() != Engine.getCurrentTopUpdateID())
		{
			//System.out.println("makin new gamestate");
			currentState = gameStateGenerator.generateGameState(this);
		}
		return currentState;
	}

}
