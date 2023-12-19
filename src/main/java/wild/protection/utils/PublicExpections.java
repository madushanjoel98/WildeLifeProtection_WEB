package wild.protection.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicExpections extends Exception{
	final Logger logger = LoggerFactory.getLogger(AdminExpections.class);
	private String message;

	public PublicExpections(String message) {
		super();
		this.message = message;
		logger.error(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
