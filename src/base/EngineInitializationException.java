package base;

/*
 * Called whenever something tries to access an engine level variable via the Engine class and it would return null. It should never return null
 */
public class EngineInitializationException extends RuntimeException {

	static final long serialVersionUID = 123;
	
	public EngineInitializationException() {
		// TODO Auto-generated constructor stub
	}

	public EngineInitializationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EngineInitializationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EngineInitializationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EngineInitializationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
