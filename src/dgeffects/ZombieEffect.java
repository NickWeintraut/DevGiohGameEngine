package dgeffects;

import dgcards.CreatureCard;
import dgconditions.EnteredFromHandCondition;
import dgconditions.InGraveyardCondition;
import dgconditions.OtherCreaturesDiedCondition;
import base.AppliedEffect;
import base.Priority;
import base.UnionCondition;

public class ZombieEffect extends AppliedEffect {

	static final long serialVersionUID = 567;
	
	public ZombieEffect(CreatureCard source) {
		super(new CreatureMoveToField(source), new UnionCondition(new EnteredFromHandCondition(source), new UnionCondition(new OtherCreaturesDiedCondition(source), new InGraveyardCondition(source))), source, true, Priority.YIELD, "Zombie");
	}
}
