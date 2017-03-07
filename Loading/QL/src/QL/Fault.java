package QL;

public abstract class Fault {

	private final String message;
	private final int line;
	
	public Fault(String message, int line) {
		this.message = message;
		this.line = line;
	}
	
	public abstract String show();
	
	protected String getFault() {
		return message + " on line " + line;
	}

}
