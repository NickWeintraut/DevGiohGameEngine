package dgconditions;

import java.util.LinkedList;
import java.util.List;
import base.Condition;
import base.Engine;
import base.Player;
import dggameComponents.DGPlayer;
import dgcards.CreatureCard;
import dgcards.Enhanceable;

public class PlayEnhancementCondition extends Condition {

	static final long serialVersionUID = 1235;
	
	DGPlayer player;
	List<CreatureCard> enhanceables;
	
	public PlayEnhancementCondition(DGPlayer player) {
		this.player = player;
		this.enhanceables = new LinkedList<CreatureCard>();
	}
	/**
	 * Gets a list of all enhanceable creature cards this condition found
	 * @return All creature cards that are legal for enhancement
	 */
	public List<CreatureCard> getEnhanceables()
	{
		return enhanceables;
	}

	/**
	 * Checks if there are any creatures on the battlefield that can be enhanced
	 * @return true if there are any currently enhanceable creatures, false if there are none
	 */
	@Override
	protected boolean checkCondition() {
		
		enhanceables.clear();
		for(Player player : Engine.getCurrentGame().getPlayerOrder().getPlayers())
		{
			//so much casting, should convert design to utilize generics
			for(CreatureCard creature : ((DGPlayer) player).getField().getCreatureCards())
			{
				if(creature instanceof Enhanceable && creature.canBeEnhanced())
					enhanceables.add(creature);
			}
		}

		//returns true if there are no enhanceables
		if(enhanceables.isEmpty())
			return false;
		else
			return true;
	}

}
