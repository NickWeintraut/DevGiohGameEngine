package dgphases;

import dggameComponents.DGPlayer;
import base.Player;
import base.PlayerDeathException;

public class DrawPhase extends DGPhase {

	public DrawPhase(Player owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performPhaseActions() throws PlayerDeathException {
		if(owner instanceof DGPlayer)
		{
			DGPlayer player = ((DGPlayer) owner);
			if(player.getDeck().getCards().size() == 0)
			{
				throw new PlayerDeathException(player, "tried to draw from an empty library.");
			}
			else
				player.drawCard();
		}
		System.out.println(owner.getName() + " just drew!");
		System.out.print("\n \n \n \n \n");
		updateUpdatables();
	}
	
	public String toString()
	{
		return "Draw Phase";
	}

}
