package dgphases;

import base.Engine;
import base.PhaseStateChangeManager;
import base.Player;
import dggameComponents.DGPlayer;

public class BeginGamePhase extends DGPhase {

	public BeginGamePhase() {
		super(null);
	}
	
	public void run()
	{
		performPhaseActions();
	}

	@Override
	public void performPhaseActions() {
		for(Player player : Engine.getCurrentGame().getPlayerOrder().getPlayers())
		{
			((DGPlayer) player).getDeck().shuffle();
			((DGPlayer) player).mulligan();
			//System.out.println(((DGPlayer) player).getHand().getCards().size());
		}
	}
	
	@Override
	public PhaseStateChangeManager createChangeManager()
	{
		return null;
	}

}
