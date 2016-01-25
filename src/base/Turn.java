package base;

/*
 * Turn will hold a collection of all the phases within a turn. It will interact
 * with the phases and play through them for each player's turn.
 * 
 * @author Olivia Lam
 * @author Nick Weintraut (who basically rewrote the entire class to work in the game engine)
 * */


public abstract class Turn extends Phase {
	
	PhaseSequence turnPhases;

	public Turn(Player owner)
	{
		super(owner);
		turnPhases = new PhaseSequence();
		populate();
	}
	
	public Phase getCurrentPhase()
	{
		return turnPhases.getCurrentPhase();
	}
	
	public void exit()
	{
		turnPhases.destroyRemainingPhases();
	}
	
	public void addPhase(Phase phase)
	{
		turnPhases.addPhase(phase);
	}
	
	public void addPhaseAfter(Phase phase, Phase markPhase)
	{
		turnPhases.addPhaseAfter(phase, markPhase);
	}
	
	abstract protected void populate();
	
	public void performPhaseActions()
	{
		turnPhases.run();
	}
	
}
