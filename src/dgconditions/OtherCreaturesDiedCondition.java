package dgconditions;

import java.util.HashSet;

import base.Card;
import base.Engine;
import base.Player;
import dgcards.CreatureCard;
import dggameComponents.DGPlayer;

public class OtherCreaturesDiedCondition extends CreatureCondition {

	static final long serialVersionUID = 42;
	
	public OtherCreaturesDiedCondition(CreatureCard creature) {
		super(creature);
		
	}

	@Override
	protected boolean checkCondition() {
		HashSet<Card> cardsMovedToGraveyard = new HashSet<Card>(); 
		for(Player player : Engine.getCurrentGame().getPlayerOrder().getPlayers())
		{
			DGPlayer dgPlayer = (DGPlayer) player;
			cardsMovedToGraveyard.addAll(Engine.getCurrentStateChangeManager().getCardsMoved(dgPlayer.getGraveyard(), dgPlayer.getField()));
		}
		
		if(cardsMovedToGraveyard.size() > 0)
		{
			//System.out.println("Cards moved to the graveyard this update step");
			boolean creatureFound = false;
			for(Card card : cardsMovedToGraveyard)
			{
				if(card.equals(creature))
				{
					creatureFound = true;
				}
			}
			return !creatureFound;
		}
		else
			return false;
	}

}
