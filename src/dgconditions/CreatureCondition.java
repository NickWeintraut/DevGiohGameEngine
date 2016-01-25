package dgconditions;


import base.Condition;
import dgcards.CreatureCard;

public abstract class CreatureCondition extends Condition{

	static final long serialVersionUID = 229;
	
	protected CreatureCard creature;
	
	public CreatureCondition(CreatureCard creature) {
		super();
		this.creature = creature;
	}
	
	public CreatureCard getCreature()
	{
		return this.creature;
	}

}
