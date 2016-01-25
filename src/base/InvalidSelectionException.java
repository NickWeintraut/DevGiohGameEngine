package base;

//its an invalid selection!!
public class InvalidSelectionException extends Exception {

	static final long serialVersionUID = 28;
	
	public InvalidSelectionException(boolean contained, int min, int max) {
		
	}

	public InvalidSelectionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidSelectionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidSelectionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidSelectionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
