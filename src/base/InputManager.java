package base;


/**
 * Input manager will take input requests from objects in the game to either 
 * re-order arrays of Activatables, select a target or targets from a collection of
 * game objects, or answer a yes or no question.
 * @author Nick
 *
 */
public class InputManager {
	
	InputResponder responder;

	public InputManager(InputResponder responder) {
		setResponder(responder);
	}
	
	public InputRequest ask(InputRequest request)
	{
		responder.takeInputRequest(request);
		Thread b = new Thread();
		synchronized (b) {
			while(!request.hasAnswer())
			{
				try {
					b.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return request;
	}


	public void setResponder(InputResponder responder) {
		this.responder = responder;
	}

}
