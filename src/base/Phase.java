package base;

//phases, should be explanatory
public abstract class Phase extends UpdaterGameObject implements Activatable, Ownable, Runnable {
	
	static final long serialVersionUID = 29;
	
	private boolean isActive;
	private boolean isBeginning;
	private boolean isEnding;
	protected Player owner;
	private PhaseStateChangeManager changeManager;

	public Phase(Player owner)
	{
		//inactive by default
		isActive = false;
		isBeginning = false;
		isEnding = false;
		this.owner = owner;
		changeManager = createChangeManager();
		if(changeManager != null)
			addUpdatable(changeManager);
		addUpdatable(Engine.getCurrentGame());
	}
	
	public abstract PhaseStateChangeManager createChangeManager();
	
	public Player getOwner()
	{
		return owner;
	}
	
	public boolean isActive() 
	//change late. it will activate/deactivate phases in accordance to what is being used current
	{
		if(isActive)
			return true;
		else
			return false;
	}
	
	public void activate()
	{
		isActive = true;
	}

	public void deactivate()
	{
		isActive = false;
	}
	
	public void run()
	{
		activate();
		//phase starts and lets the game know
		isBeginning = true;
		updateUpdatables();
		isBeginning = false;
		
		// this will be the flesh of individual phases
		try
		{
			performPhaseActions();
		} catch (PlayerDeathException e)
		{
			Player deadPlayer = e.getPlayer();
			String message = e.getMessage();
			System.out.println(deadPlayer.getName() + message);
			deadPlayer.destroy();
			updateUpdatables();
		}		
		//phase ends and lets the game know
		isEnding = true;
		updateUpdatables();
		isEnding = false;
		
		deactivate();
	}
	
	//overwrites updateUpdatables to iterate the engine's updateID before updating
	public void updateUpdatables()
	{
		Engine.nextTopUpdateID();
		super.updateUpdatables();
	}
	
	public abstract void performPhaseActions() throws PlayerDeathException;
	
	public boolean isBeginning()
	{
		return isBeginning;
	}
	
	public boolean isEnding()
	{
		return isEnding;
	}
	
}
