package dgeffects;

import base.AppliedEffect;
import base.Priority;
import dgconditions.CreatureDeathCondition;
import dgcards.CreatureCard;

public class CreatureDeathCheckEffect extends AppliedEffect {

	static final long serialVersionUID = 321;
	
	public CreatureDeathCheckEffect(CreatureCard source) {
		super(new CreatureDeath(source), new CreatureDeathCondition(source), source, true, Priority.PASS, "");
	}
}
