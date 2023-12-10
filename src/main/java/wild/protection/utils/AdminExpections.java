package wild.protection.utils;

public class AdminExpections extends Exception{

	private String message;

	public AdminExpections(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
