package QL.errorhandling;

public class Error extends Fault {
	
	public Error(String message, int line) {
		super(message, line);
	}
	
	public String show() {
		return "Error: " + getFault();
	}
}
