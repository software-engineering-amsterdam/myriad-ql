package semantic;
import java.util.HashMap;
import java.util.Map;

import QL.Error;
import QL.Faults;
import ast.type.Type;
import ast.type.UnknownType;

public class Environment {

	private Map<String, Type> variableType; // Should these two be combined to label - variable/type
	private Map<String, String> labelVariable;
	private Faults faults;
	
	public Environment() {
		variableType = new HashMap<>(); 
		labelVariable = new HashMap<>();
		faults = new Faults();
	}
	
	public Faults getFaults() {
		return faults;
	}
	
	public Map<String, Type> getVariableTypes() {
		return variableType;
	}
	
	public void addLabel(String label, String variableName) {
		labelVariable.put(label, variableName);
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
	
	public boolean labelExists(String label) {
		return labelVariable.containsKey(label);
	}
	
	
}
