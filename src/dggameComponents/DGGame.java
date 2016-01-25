package dggameComponents;

import java.util.LinkedList;

import base.GameOutputable;
import base.InputResponder;
import base.ActivationStackManager;
import base.Game;
import base.GameStateGenerator;
import base.InputManager;
import base.Player;
import base.PlayerOrder;
import base.StateChangeManager;
import base.TurnManager;

public class DGGame extends Game {

	public DGGame(LinkedList<Player> players, GameOutputable gameOutput) {
		super(players, gameOutput);
	}

	@Override
	protected InputManager createInputManager(InputResponder gameOutput) {
		return new InputManager(gameOutput);
	}

	@Override
	protected PlayerOrder createPlayerOrder(LinkedList<Player> players) {
		return new PlayerOrder(players);
	}

	@Override
	protected TurnManager createTurnManager() {
		return new DGTurnManager();
	}

	@Override
	protected GameStateGenerator createGameStateGenerator() {
		return new GameStateGenerator();
	}

	@Override
	protected StateChangeManager createStateChangeManager() {
		return new StateChangeManager();
	}

	@Override
	protected ActivationStackManager createActivationStackManager() {
		return new ActivationStackManager();
	}

}
