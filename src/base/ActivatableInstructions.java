package base;

/*
 * Stores information on an activatable and whether or not to activate it or deactivate it
 */
public class ActivatableInstructions extends GameObject{
	
	static final long serialVersionUID = 314;
	
	private Activatable activatable;
	private boolean shouldActivate;
	
	private Player source;

	public ActivatableInstructions(Activatable activatable, boolean shouldActivate, Player source) {
		this.activatable = activatable;
		this.shouldActivate = shouldActivate;
		this.source = source;
	}

	public Activatable getActivatable() {
		return activatable;
	}

	public boolean isShouldActivate() {
		return shouldActivate;
	}

	public void performInstructions()
	{
		if(shouldActivate)
			activatable.activate();
		else
			activatable.deactivate();
	}

	public Player getSource() {
		return source;
	}
}
