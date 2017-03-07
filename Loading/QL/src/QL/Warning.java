package QL;


public class Warning {
	
	private final String message;
	private final int line;
	
	public Warning(String message, int line) {
		this.message = message;
		this.line = line;
	}
	
	public String show() {
		return "Warning: " + message + " on line " + line;
	}
	
}
