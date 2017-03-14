package qls.semantic;

import java.util.HashMap;
import java.util.Map;

import QL.Faults;
import QL.ast.type.Type;
import QL.errorhandling.Error;

public class Environment {
	
	private final Map<String, Type> variableTypes;
	private Map<String, Boolean> variableCovered;
	private Faults faults;
	
	public Environment(Map<String, Type> variableTypes) {
		this.variableTypes = variableTypes;
		this.variableCovered = new HashMap<String, Boolean>();
		for (String variable : variableTypes.keySet()) {
			variableCovered.put(variable, false);
		}
		this.faults = new Faults(); // TODO move to analyzing part
	}
	
	public Faults getFaults() {
		return faults;
	}
	
	public void isCovered(String name, int line) {
		
		// TODO move to separate functions
		if (!variableCovered.containsKey(name)) {
			faults.add(new Error("The variable " + name + 
					" appears in the QLS, but does not exist in QL", line));
		}
		else if (variableCovered.get(name)) {
			faults.add(new Error("The variable " + name + 
					" is already defined int the QLS", line));
		}
		else {
			variableCovered.replace(name, true);	
		}
	}
	
	// TODO fault without line number
	public void checkCoverage() {
		for (String name : variableTypes.keySet()) {
			if (!variableCovered.get(name)) {
				faults.add(new Error("The variable " + name + " is not defined in QLS", 0));
			}
		}
	}

	
	
}
