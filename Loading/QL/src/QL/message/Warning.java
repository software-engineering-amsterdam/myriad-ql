package QL.message;


public class Warning extends Message {
	
	public Warning(String body, int line) {
		super(body, line);
	}

	@Override
	public boolean isFatal() {
		return false;
	}

	@Override
	public String getMessage() {
		return "Warning: " + buildMessage();
	}
	
}
