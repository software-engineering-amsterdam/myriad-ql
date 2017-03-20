package qls.semantic;

import java.util.HashMap;
import java.util.Map;

import QL.Faults;
import QL.ast.type.Type;
import QL.errorhandling.Error;

class Environment {
	
	private final Map<String, Boolean> variableCovered;
	private final Faults faults;
	
	public Environment(Map<String, Type> variableTypes) {
		
		this.variableCovered = new HashMap<>();		
		for (String variable : variableTypes.keySet()) {
			variableCovered.put(variable, false);
		}
		this.faults = new Faults(); // TODO move to analyzing part
	}
	
	public Faults getFaults() {
		return faults;
	}
	
//	public void isCovered(String name, int line) {
//		
//		// TODO move to separate functions
//
//		else if (variableCovered.get(name)) {
//			faults.add(new Error("The variable " + name + 
//					" is already defined int the QLS", line));
//		}
//		else {
//			variableCovered.replace(name, true);	
//		}
//	}
	
	public boolean presentInQL(String name) {
		
		return variableCovered.containsKey(name);
		
//		if (!variableCovered.containsKey(name)) {
//			faults.add(new Error("The variable " + name + 
//					" appears in the QLS, but does not exist in QL", line));
//			return false;
//		}
//		return true;
	}
	
	public boolean isCovered(String name) {
		return variableCovered.get(name);
	}
	
	public void setCovered(String name) {
		variableCovered.replace(name, true);
	}
	
	// TODO fault without line number
	public void checkCoverage() {
		for (String name : variableCovered.keySet()) {
			if (!variableCovered.get(name)) {
				faults.add(new Error("The variable " + name + 
						" is not defined in QLS", 0));
			}
		}
	}
	
}
