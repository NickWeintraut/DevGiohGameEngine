package base;

//basically AND for conditions, both condition fields must be true
public class UnionCondition extends Condition {

	static final long serialVersionUID = 6;
	
	Condition con1;
	Condition con2;
	
	public UnionCondition(Condition con1, Condition con2) {
		this.con1 = con1;
		this.con2 = con2;
	}

	@Override
	protected boolean checkCondition() {
		con1.update();
		con2.update();
		if(con1.isConditionMet() && con2.isConditionMet())
			return true;
		else
			return false;
	}

}
