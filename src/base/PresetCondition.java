package base;

//condition that always returns a set value lol
public class PresetCondition extends Condition {

	static final long serialVersionUID = 15;
	
	boolean isMet;
	
	public PresetCondition(boolean isMet) {
		this.isMet = isMet;
	}

	@Override
	protected boolean checkCondition() {
		
		return isMet;
	}

}
