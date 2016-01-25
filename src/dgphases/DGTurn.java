package dgphases;

import base.PhaseStateChangeManager;
import base.Player;
import base.Turn;

public class DGTurn extends Turn {

	public DGTurn(Player owner) {
		super(owner);
	}

	@Override
	protected void populate() {
		addPhase(new DrawPhase(getOwner()));
		addPhase(new PlayPhase(getOwner()));
		addPhase(new AttackPhase(getOwner()));
	}

	@Override
	public PhaseStateChangeManager createChangeManager() {
		return new PhaseStateChangeManager(this);
	}

}
