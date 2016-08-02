package base;

/*
 * Stores information on an activatable and whether or not to activate it or deactivate it
 */
public class ActivatableInstructions extends GameObject{
	
	static final long serialVersionUID = 314;
	
	private Activatable activatable;
	private boolean shouldActivate;
	
	private Player source;

	/**
	 * Create an ActivatableInstruction such that performing the instruction will either deactivate or activate the effect
	 * @param activatable The activatable to operate on
	 * @param shouldActivate Should we activate or deactivate when calling performInstruction?
	 * @param source The player that this Activatable comes from
	 */
	public ActivatableInstructions(Activatable activatable, boolean shouldActivate, Player source) {
		this.activatable = activatable;
		this.shouldActivate = shouldActivate;
		this.source = source;
	}

	/**
	 * @return The activatable we'll activate/deactivate
	 */
	public Activatable getActivatable() {
		return activatable;
	}

	/**
	 * 
	 * @return True if this instruction will activate the activatable, False if else
	 */
	public boolean isShouldActivate() {
		return shouldActivate;
	}

	/**
	 * Activates or deactivates the activatable
	 */
	public void performInstructions()
	{
		if(shouldActivate)
			activatable.activate();
		else
			activatable.deactivate();
	}

	/**
	 * Get the player this instruction came from
	 * @return The player who's effects created this instruction
	 */
	public Player getSource() {
		return source;
	}
}
