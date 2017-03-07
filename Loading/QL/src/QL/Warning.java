package QL;


public class Warning extends Fault {
	
	public Warning(String message, int line) {
		super(message, line);
	}

	public String show() {
		return "Warning: " + getFault();
	}
	
}
