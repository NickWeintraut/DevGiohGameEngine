package base;

//interface for an inputresponder that the game requires, btw i jsut realized i dont think this needs to implement updatable 
//as gameoutputable does so but i dont feel like accidentally breaking things due to forgetting some weird thing
public interface InputResponder extends Updatable{
	
	public InputRequest takeInputRequest(InputRequest inputRequest);

}
