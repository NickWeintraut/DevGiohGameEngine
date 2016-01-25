package dgphases;

import base.Phase;
import base.PhaseStateChangeManager;
import base.Player;

public abstract class DGPhase extends Phase {

	public DGPhase(Player owner) {
		super(owner);
	}

	@Override
	public PhaseStateChangeManager createChangeManager() {

		return new PhaseStateChangeManager(this);
	}

}
