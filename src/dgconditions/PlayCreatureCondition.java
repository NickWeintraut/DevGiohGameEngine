package dgconditions;

import base.Condition;

public class PlayCreatureCondition extends Condition {

	static final long serialVersionUID = 1234;
	
	public PlayCreatureCondition() {
		
	}

	@Override
	protected boolean checkCondition() {
		//System.out.println("checkedIt");
		return true;
	}

}