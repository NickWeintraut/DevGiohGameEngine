package dgconditions;

import dgcards.CreatureCard;
import dggameComponents.DGPlayer;

public class InGraveyardCondition extends CreatureCondition {

	static final long serialVersionUID = 388;
	
	public InGraveyardCondition(CreatureCard creature) {
		super(creature);
	}

	@Override
	protected boolean checkCondition() {
		if(((DGPlayer)creature.getOwner()).getGraveyard().hasCard(creature))
			return true;
		else
			return false;
	}

}
