package dgphases;

import java.util.LinkedList;
import java.util.List;

import dggameComponents.DGPlayer;
import dginput.CreatureSelectionRequest;
import base.Card;
import base.CardSelectionRequest;
import base.CardZone;
import base.Engine;
import base.Player;
import dgcards.CreatureCard;
import dgcards.EnhancementCard;

public class PlayPhase extends DGPhase {

	public PlayPhase(Player owner) {
		super(owner);
	}

	@Override
	public void performPhaseActions() {
		
		List<CreatureCard> creaturesInHand = ((DGPlayer) owner).getHand().getCreatureCards();
		//System.out.println(creaturesInHand.size());
		List<Card> playableCreatures = new LinkedList<Card>();
		for(CreatureCard creature : creaturesInHand)
		{
			//System.out.println(creature.findPlayCondition().isConditionMet());
			if(creature.isPlayable())
				playableCreatures.add(creature);
		}
		
		if(playableCreatures.size() > 0)
		{
			CardSelectionRequest creatureToPlay = new CardSelectionRequest("You may select a creature to play.", this.owner, playableCreatures, 0, 1);
			Engine.getCurrentInputManager().ask(creatureToPlay);
			if(creatureToPlay.getSelectedCards().size() > 0)
			{
				CreatureCard creature = (CreatureCard) creatureToPlay.getSelectedCards().get(0);
				CardZone.moveCard(creature, ((DGPlayer) owner).getHand(), ((DGPlayer) owner).getField());
				updateUpdatables();
			}
		}
		
		
		List<EnhancementCard> enhancementsInHand = ((DGPlayer) owner).getHand().getEnhancementCards();
		List<Card> playableEnhancements = new LinkedList<Card>();
		for(EnhancementCard enhancement : enhancementsInHand)
		{
			if(enhancement.isPlayable())
				playableEnhancements.add(enhancement);
		}
		
		if(playableEnhancements.size() > 0)
		{
			CardSelectionRequest enhancementToPlay = new CardSelectionRequest("You may select an enhancement to play.", this.owner, playableEnhancements, 0, 1);
			Engine.getCurrentInputManager().ask(enhancementToPlay);
			if(enhancementToPlay.getSelectedCards().size() > 0)
			{
				EnhancementCard enhancement = (EnhancementCard) enhancementToPlay.getSelectedCards().get(0);
				
				CreatureSelectionRequest creatureToEnhance = new CreatureSelectionRequest("You may select a creature to enhance.", this.owner, ((DGPlayer) owner).getPlayEnhancementCondition().getEnhanceables(), 0, 1);
				Engine.getCurrentInputManager().ask(creatureToEnhance);
				if(creatureToEnhance.getSelectedCreatures().size() > 0)
				{
					CreatureCard creature = (CreatureCard) creatureToEnhance.getSelectedCreatures().get(0);
					
					enhancement.enhance(creature);
					enhancement.destroy();
				}
				updateUpdatables();
			}
		}

	}
	
	public String toString()
	{
		return "Play Phase";
	}

}
