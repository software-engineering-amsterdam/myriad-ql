package QL.message;

public class Error extends Message {
	
	public Error(String body, int line) {
		super(body, line);
	}

	@Override
	public boolean isFatal() {
		return true;
	}

	@Override
	public String getBody() {
		return "Error: " + buildMessage();
	}
}
