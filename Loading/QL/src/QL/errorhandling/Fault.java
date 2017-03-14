package QL.errorhandling;

public abstract class Fault {

	private final String message;
	private final int line;
	
	Fault(String message, int line) {
		this.message = message;
		this.line = line;
	}
	
	public abstract String show();
	
	String getFault() {
		return message + " on line " + line;
	}

}
