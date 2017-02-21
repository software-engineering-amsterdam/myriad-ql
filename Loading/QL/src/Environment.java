
import java.util.HashMap;
import java.util.Map;

import ast.type.Type;

public class Environment {

	private Map<String, Type> variableType; // Should these two be combined to label - variable/type
	private Map<String, String> labelVariable;
	
	Environment() {
		variableType = new HashMap<>(); 
		labelVariable = new HashMap<>();
	}
	
	public void addLabel(String label, String variableName) {
		labelVariable.put(label, variableName);
	}
	
	public void addVariableType(String variable, Type type) {
		variableType.put(variable, type);
	}
	
	public boolean variableExists(String variable) {
		return variableType.containsKey(variable);
	}
	
	public boolean labelExists(String label) {
		return labelVariable.containsKey(label);
	}
	
	
}
