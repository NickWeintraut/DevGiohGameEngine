package base;

/**
 * While active, a TriggeredEffect will send it's effect to the activation stack at each update call.
 * The activation stack should deactivate all triggered effects before any calls to game.tick()
 * @author Nick
 *
 */
public class AppliedEffect extends DecoratedEffect {

	static final long serialVersionUID = 256;
	
	private Condition triggerCondition;
	private boolean stack;
	
	public AppliedEffect(Effect effect, Condition condition, Card source, boolean stack, Priority priority, String name) {
		super(effect, source, priority, name);
		this.triggerCondition = condition;
		this.stack = stack;
		addUpdatable(condition);
		activate();
	}
	
	public void update()
	{
		//System.out.println("whats going on");
		if(isActive())
		{
			updateUpdatables();
			if(triggerCondition.isConditionMet())
			{
				Engine.getCurrentGame().getActivationStack().receiveActivatable(this.effect, priority, stack, owner);
			}
		}
	}
	
	public Condition getCondition()
	{
		return triggerCondition;
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
