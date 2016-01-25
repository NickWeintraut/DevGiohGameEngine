package base;

import java.util.LinkedList;

//sequence of phases with methods for adding to it while its iterating (for whatever crazy mechanics you might want to implement with granting additional phases)
public class PhaseSequence implements Runnable {

	private LinkedList<Phase> phases;
	private Phase currentPhase;
	
	public PhaseSequence() {
		phases = new LinkedList<Phase>();
	}
	
	public Phase getCurrentPhase()
	{
		return currentPhase;
	}
	
	public void addPhase(Phase phase)
	{
		phases.add(phase);
	}
	
	public void clearSequence()
	{
		phases.clear();
	}
	
	public void addPhaseAfter(Phase phase, Phase markPhase)
	{
		if(phases.contains(markPhase))
		{
			int markIndex = phases.indexOf(markPhase);
			if(markIndex == (phases.size() - 1))
				phases.addLast(phase);
			else
				phases.add(markIndex + 1, phase);
		}
		else
			phases.addLast(phase);
	}
	
	public void run()
	{
		while(hasNextPhase())
		{
			currentPhase = getNextPhase();
			currentPhase.run();
		}
	}
	
	public void destroyRemainingPhases()
	{
		phases.clear();
	}
	
	public Phase getNextPhase() //get the next phase
	{
		Phase ph = null;
		if(phases.size() > 0)
		{
			if(currentPhase == null)
				ph = phases.getFirst();
			else if(hasNextPhase())
				ph = phases.get(phases.indexOf(currentPhase) + 1);
		}
		
		return ph;
	}
	
	public boolean hasNextPhase() // if there is a next phase or not
	{
		if(phases.indexOf(currentPhase) < (phases.size() - 1))
		{
			return true;
		}
		else
			return false;
	}

}
