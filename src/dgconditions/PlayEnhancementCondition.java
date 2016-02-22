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
		
		List<CreatureCard> creaturesToCheck = new LinkedList<CreatureCard>();
		List<DGPlayer> dgPlayers = new LinkedList<DGPlayer>();
		//add all players to a linkedlist (does this need to be done? Why not just use list from PlayerOrder)
		for(Player player : Engine.getCurrentGame().getPlayerOrder().getPlayers())
		{
			dgPlayers.add((DGPlayer) player);
		}
		//add all creature cards from each field to a list of creatures to check
		for(DGPlayer dgPlayer : dgPlayers)
		{
			creaturesToCheck.addAll(dgPlayer.getField().getCreatureCards());
		}
		//add all creatures that are enhanceable and can currently be enhanced
		List<CreatureCard> validCreatures = new LinkedList<CreatureCard>();
		for(CreatureCard creature : creaturesToCheck)
		{
			if(creature instanceof Enhanceable && creature.canBeEnhanced())
				validCreatures.add(creature);
		}
		//clear current enhanceables and add the current list
		enhanceables.clear();
		enhanceables.addAll(validCreatures);
		if(enhanceables.isEmpty())
			return false;
		else
			return true;
	}

}
