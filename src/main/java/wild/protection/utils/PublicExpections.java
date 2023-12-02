package wild.protection.utils;

public class PublicExpections extends Exception{

	private String message;

	public PublicExpections(String message) {
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
