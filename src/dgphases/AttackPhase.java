package dgphases;

import java.util.Iterator;
import java.util.List;

import dgcards.CreatureCard;
import dggameComponents.DGPlayer;
import dginput.CreatureSelectionRequest;
import base.BooleanInputRequest;
import base.Engine;
import base.Player;
import base.PlayerSelectionRequest;

public class AttackPhase extends DGPhase {

	public AttackPhase(Player owner) {
		super(owner);
	}

	@Override
	public void performPhaseActions() {
		if(((DGPlayer) owner).getField().getCards().size() > 0)
		{
			BooleanInputRequest attackQuestion = new BooleanInputRequest("Would you like to attack?", owner);
			Engine.getCurrentInputManager().ask(attackQuestion);
			if(attackQuestion.getAnswer())
			{
				//get which creatures to attack with
				List<CreatureCard> creaturesOnField = ((DGPlayer) owner).getField().getCreatureCards();
				CreatureSelectionRequest creaturesToAttackWith = new CreatureSelectionRequest("Which creatures will you attack with?", owner, creaturesOnField, 0, creaturesOnField.size());
				Engine.getCurrentInputManager().ask(creaturesToAttackWith);
				List<CreatureCard> attackingCreatures = creaturesToAttackWith.getSelectedCreatures();
				
				//get which players to attack
				List<Player> opposingPlayers = Engine.getCurrentGame().getPlayerOrder().getOpposingPlayers();
				PlayerSelectionRequest playersToAttack = new PlayerSelectionRequest("Which players should you attack?", owner, opposingPlayers, 0, opposingPlayers.size());
				Engine.getCurrentInputManager().ask(playersToAttack);
				List<Player> attackedPlayers = playersToAttack.getSelectedPlayers();
				
				Iterator<Player> victimIt = attackedPlayers.iterator();
				
				while(victimIt.hasNext() && !attackingCreatures.isEmpty())
				{
					DGPlayer attackedPlayer = (DGPlayer) victimIt.next();
					List<CreatureCard> blockers = attackedPlayer.getField().getCreatureCards();
					while(!blockers.isEmpty() && !attackingCreatures.isEmpty())
					{
						//Get the selected blocker
						CreatureSelectionRequest blockerToAttack = new CreatureSelectionRequest("Which creature would you like to attack?", owner, blockers, 1, 1);
						Engine.getCurrentInputManager().ask(blockerToAttack);
						CreatureCard attackedBlocker = blockerToAttack.getSelectedCreatures().get(0);
						
						//get which creatures should attack that blocker
						CreatureSelectionRequest attackersForBlocker = new CreatureSelectionRequest("Which of your attacking creatures should attack " + attackedBlocker.getName(), owner, attackingCreatures, 0, attackingCreatures.size());
						Engine.getCurrentInputManager().ask(attackersForBlocker);
						List<CreatureCard> damagers = attackersForBlocker.getSelectedCreatures();
						attackingCreatures.removeAll(damagers);
						
						//deal damage and stuff
						for(CreatureCard creature : damagers)
						{
							creature.attack(attackedBlocker);
						}
						updateUpdatables();
						//System.out.println("wtf");
						if(attackedBlocker.getCurrentZone().equals(((DGPlayer) attackedBlocker.getOwner()).getGraveyard()))
						{
							blockers.remove(attackedBlocker);
						}
					}
					if(!attackingCreatures.isEmpty())
					{
						//ask which creatures should attack the victim player
						CreatureSelectionRequest damagersForVictim = new CreatureSelectionRequest("Which creatures would you like to attack " + attackedPlayer.getName(), owner, attackingCreatures, 0, attackingCreatures.size());
						Engine.getCurrentInputManager().ask(damagersForVictim);
						List<CreatureCard> damagers = damagersForVictim.getSelectedCreatures();
						attackingCreatures.removeAll(damagers);
						
						for(CreatureCard damager : damagers)
						{
							damager.attack(attackedPlayer);
						}
						
						updateUpdatables();
						
						if(!attackedPlayer.isAlive())
						{
							victimIt.remove();
						}
						
					}
				}
			}
		}
		

	}
	
	public String toString()
	{
		return "Attack Phase";
	}

}
