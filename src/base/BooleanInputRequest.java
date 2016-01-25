package base;

/*
 * An input request that wants a boolean, huh
 */
public class BooleanInputRequest extends InputRequest {

	private boolean answer;
	private boolean hasAnswered;
	
	public BooleanInputRequest(String message, Player owner) {
		super(message, owner);
	}
	
	public void setAnswer(boolean answer)
	{
		this.answer = answer;
		hasAnswered = true;
	}
	
	public boolean getAnswer()
	{
		return answer;
	}

	@Override
	public boolean hasAnswer() {
		return hasAnswered;
	}

}
