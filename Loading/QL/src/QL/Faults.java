package QL;

import java.util.ArrayList;
import java.util.List;

public class Faults {
	
	private List<Warning> warnings;
	private List<Error> errors;
	
	public Faults() {
		warnings = new ArrayList<Warning>();
		errors = new ArrayList<Error>();
	}
	
	public boolean hasWarnings() {
		return !warnings.isEmpty();
	}
	
	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	public void add(Warning warning) {
		warnings.add(warning);
	}
	
	public void add(Error error) {
		errors.add(error);
	}
	
	public List<Warning> getWarnings() {
		return warnings;
	}
	
	public List<Error> getErrors() {
		return errors;
	}

	
}
