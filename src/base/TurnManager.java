package base;

//manages granting turns to each player using the playerorder and interating through them
public abstract class TurnManager extends GameObject implements Runnable, Activatable {

	private PhaseSequence turns;
	private boolean isActive;
	
	public TurnManager() {
		turns = new PhaseSequence();
	}

	@Override
	public void run() {
		while(isActive())
		{
			turns.run();
			turns.clearSequence();
			addNextTurn();
		}
	}
	
	public void exitCurrentTurn()
	{
		getCurrentTurn().exit();
	}
	
	public Turn getCurrentTurn()
	{
		Phase turn = turns.getCurrentPhase();
		if(turn instanceof Turn)
			return (Turn) turn;
		else
			return null;
	}
	
	public Player getCurrentTurnPlayer()
	{
		return turns.getCurrentPhase().getOwner();
	}
	
	public void addNextTurn()
	{
		PlayerOrder order = Engine.getCurrentGame().getPlayerOrder();
		order.nextPlayer();
		addTurn(order.getCurrentPlayer());
	}
	
	public abstract Turn getNewTurn(Player player);
	
	public void addTurn(Player player)
	{
		turns.addPhase(getNewTurn(player));
	}
	
	public void addPrePhase(Phase phase)
	{
		turns.addPhase(phase);
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public void activate()
	{
		isActive = true;
	}
	
	public void deactivate()
	{
		isActive = false;
	}

}
