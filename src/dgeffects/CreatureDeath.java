package dgeffects;

import dggameComponents.DGPlayer;
import dgcards.CreatureCard;
import base.CardZone;
import base.Effect;

public class CreatureDeath extends Effect {

	static final long serialVersionUID = 557;
	
	public CreatureDeath(CreatureCard source) {
		super(source, source.getName() + "Death");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate() {
		DGPlayer player = (DGPlayer) this.source.getOwner();
		if(player != null && player instanceof DGPlayer)
		{
			//System.out.println("This creature should be destroyed like wtf!!!");
			source.destroy();
			CardZone.moveCard(source, source.getCurrentZone() , player.getGraveyard());
		}

	}

	@Override
	public void deactivate() {
		System.out.println("Silly rabbit, you can't undo creature death!");
	}

	public void update()
	{
		
	}
}
