package QL.message;

public abstract class Message {

	private final String body;
	private final int line;
	
	Message(String body, int line) {
		this.body = body;
		this.line = line;
	}

	public abstract boolean isFatal();

	public abstract String getBody();

	String buildMessage() {
		return body + " on line " + line;
	}

}
