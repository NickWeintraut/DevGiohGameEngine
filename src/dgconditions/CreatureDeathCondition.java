package dgconditions;

import base.Player;
import dgcards.CreatureCard;
import dggameComponents.DGPlayer;

public class CreatureDeathCondition extends CreatureCondition {

	static final long serialVersionUID = 257;
	
	public CreatureDeathCondition(CreatureCard creature) {
		super(creature);
	}

	@Override
	protected boolean checkCondition() {
		if(creature.getHealth() <= 0)
		{
			Player player = creature.getOwner();
			if(player != null && player instanceof DGPlayer && ((DGPlayer) player).getField().hasCard(creature))
				return true;
			else
				return false;
		}
		else
		{
			//System.out.println(System.identityHashCode(creature));
			return false;
		}
	}

}
