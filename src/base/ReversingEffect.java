package base;

import java.util.List;

//its some effect whatever there are so many classes
public class ReversingEffect extends DecoratedEffect {
	
	static final long serialVersionUID = 89;
	
	Condition condition;
	List<Updatable> updatables;
	

	public ReversingEffect(Condition condition, Effect effect, Card source, Priority priority, String name) {
		super(effect, source, priority, name);
		this.condition = condition;
		addUpdatable(condition);
	}
	
	public void activate()
	{
		isActive = true;
	}
	
	public void deactivate()
	{
		isActive = false;
	}
	
	public void update()
	{
			updateUpdatables();
			boolean conditionMet = condition.isConditionMet();
			if(conditionMet && !isActive())
			{
				Engine.getCurrentGame().getActivationStack().receiveActivatable(this.effect, priority, true, owner);
			}
			else if(!conditionMet && isActive())
			{
				Engine.getCurrentGame().getActivationStack().receiveActivatable(this.effect, priority, false, owner);
				
			}
	}

}
