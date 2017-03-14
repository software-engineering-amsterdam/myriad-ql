package QL.semantic;

import QL.Faults;
import QL.ast.type.Type;
import QL.ast.type.UnknownType;
import QL.errorhandling.Error;

import java.util.HashMap;
import java.util.Map;

public class Environment {

	private Map<String, Type> variableType; // Should these two be combined to label - variable/type
	private Faults faults;
	
	public Environment() {
		variableType = new HashMap<>();
		faults = new Faults();
	}
	
	public Faults getFaults() {
		return faults;
	}
	
	public Map<String, Type> getVariableTypes() {
		return variableType;
	}

	public void addVariableType(String variable, Type type) {
		variableType.put(variable, type);
	}
	
	public Type getType(String variable, int line) {
		
		if (!variableExists(variable)) {
	        faults.add(new Error("The variable: " + variable + " is not defined", line));
	        return new UnknownType(line);
		}
		return variableType.get(variable);
	}
	
	public boolean variableExists(String variable) {
		return variableType.containsKey(variable);
	}
}
