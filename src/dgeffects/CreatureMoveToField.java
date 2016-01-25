package dgeffects;

import dggameComponents.DGPlayer;
import base.Card;
import base.CardZone;
import base.Effect;

public class CreatureMoveToField extends Effect {
	
	static final long serialVersionUID = 348;
	
	public CreatureMoveToField(Card source) {
		super(source, "creatureMoveZone");
	}

	@Override
	public void activate() {
		CardZone.moveCard(source, source.getCurrentZone(), ((DGPlayer) source.getOwner()).getField());

	}

	@Override
	public void deactivate() {
		System.out.println("wat");

	}
	
	public void update()
	{
		
	}

}
