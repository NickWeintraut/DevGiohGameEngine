package dgconditions;

import java.util.HashSet;

import base.Condition;
import base.Engine;
import base.Card;
import dgcards.CreatureCard;
import dggameComponents.DGPlayer;

public class EnteredFromHandCondition extends CreatureCondition {
	
	boolean fromHand;
	
	public EnteredFromHandCondition(CreatureCard creature) {
		super(creature);
		// TODO Auto-generated constructor stub
		fromHand = false;
	}

	
	@Override
	protected boolean checkCondition() {
		if(Engine.getCurrentStateChangeManager().getCardsMoved(((DGPlayer)creature.getOwner()).getField(), ((DGPlayer)creature.getOwner()).getHand()).contains(creature))
		{
			//check statechange manager for change from hand to battlefield, set true.
			fromHand = true;
		}
		else if(Engine.getCurrentStateChangeManager().getCardsMoved(((DGPlayer)creature.getOwner()).getField(), ((DGPlayer)creature.getOwner()).getGraveyard()).contains(creature))
		{
			//movement to graveyard will set to false.
			fromHand = false;
		}
		
		return fromHand;
	}

}
