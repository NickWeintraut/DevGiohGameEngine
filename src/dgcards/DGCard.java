package dgcards;

import dggameComponents.DGPlayer;
import base.Card;
import base.CardZone;

public abstract class DGCard extends Card {

	static final long serialVersionUID = 2;
	
	public DGCard(String name) {
		super(name);
	}
	public DGCard(){
		super();
	}

	@Override
	public void destroy() {
		if(this.owner instanceof DGPlayer)
		{
			
			CardZone.moveCard(this, this.zone, ((DGPlayer) owner).getGraveyard());
		}

	}

}
