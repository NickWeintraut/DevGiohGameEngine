package dggameComponents;

import dgphases.DGTurn;
import dgphases.BeginGamePhase;
import base.Player;
import base.Turn;
import base.TurnManager;

public class DGTurnManager extends TurnManager {

	public DGTurnManager() {
		super();
	}
	
	public void run()
	{
		addPrePhase(new BeginGamePhase());
		super.run();
	}

	@Override
	public Turn getNewTurn(Player player) {
		return new DGTurn(player);
	}

}
