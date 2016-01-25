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
	
	public List<CreatureCard> getEnhanceables()
	{
		return enhanceables;
	}

	@Override
	protected boolean checkCondition() {
		
		List<CreatureCard> creaturesToCheck = new LinkedList<CreatureCard>();
		List<DGPlayer> dgPlayers = new LinkedList<DGPlayer>();
		for(Player player : Engine.getCurrentGame().getPlayerOrder().getPlayers())
		{
			dgPlayers.add((DGPlayer) player);
		}
		for(DGPlayer dgPlayer : dgPlayers)
		{
			creaturesToCheck.addAll(dgPlayer.getField().getCreatureCards());
		}
		List<CreatureCard> validCreatures = new LinkedList<CreatureCard>();
		for(CreatureCard creature : creaturesToCheck)
		{
			if(creature instanceof Enhanceable && creature.canBeEnhanced())
				validCreatures.add(creature);
		}
		enhanceables.clear();
		enhanceables.addAll(validCreatures);
		if(enhanceables.isEmpty())
			return false;
		else
			return true;
	}

}
