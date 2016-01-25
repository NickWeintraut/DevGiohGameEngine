package base;

//is a statechangemanager that knows what phase its for, i think its also different in that its stores changes for the whole phase and doesnt clear them every update tick. yeah thats it.
public class PhaseStateChangeManager extends StateChangeManager {

	static final long serialVersionUID = 30;
	
	//The phase for which state changes will be recorded
	protected Phase phase;
	
	public PhaseStateChangeManager(Phase phase) {
		super();
		this.phase = phase;
	}
	
	public void update()
	{
		if(isActive())
		{
			if(oldState == null)
			{
				oldState = Engine.getCurrentGame().generateGameState();
				newState = oldState;
			}
			oldState = newState;
			newState = Engine.getCurrentGame().generateGameState();
			//the false means it won't clear the game changes and just add to them
			synchronizeGameChanges(false);
		}
	}
	
	public boolean isActive()
	{
		if(phase.isActive())
			return true;
		else
			return false;
	}

	public Phase getPhase() {
		return phase;
	}
}
