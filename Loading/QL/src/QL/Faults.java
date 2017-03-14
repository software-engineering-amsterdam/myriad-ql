package QL;

import java.util.ArrayList;
import java.util.List;

import QL.errorhandling.Error;
import QL.errorhandling.Warning;
import QL.ui.error.ErrorDialog;
import QL.ui.error.WarningDialog;

public class Faults {
	
	private final List<Warning> warnings;
	private final List<Error> errors;
	
	public Faults() {
		warnings = new ArrayList<>();
		errors = new ArrayList<>();
	}
	
	// TODO this class now depends on the GUI
    public void check() {
    	if (hasErrors()) {
    		ErrorDialog dialog = new ErrorDialog(errors);
    		dialog.show();
    	}   	
    	if (hasWarnings()) {
        	WarningDialog dialog = new WarningDialog(warnings);
        	dialog.show();
    	}   	
    }
	
	private boolean hasWarnings() {
		return !warnings.isEmpty();
	}
	
	private boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	public void add(Warning warning) {
		warnings.add(warning);
	}
	
	public void add(Error error) {
		errors.add(error);
	}
	
}
