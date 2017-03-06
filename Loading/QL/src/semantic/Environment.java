package semantic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import QL.Warning;
import ast.type.Type;
import ast.type.UnknownType;

public class Environment {

	private Map<String, Type> variableType; // Should these two be combined to label - variable/type
	private Map<String, String> labelVariable;
	private List<Warning> warnings;
	
	public Environment() {
		variableType = new HashMap<>(); 
		labelVariable = new HashMap<>();
		warnings = new ArrayList<>();
	}
	
	public List<Warning> getWarnings() {
		return warnings;
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
	
	public void addWarning(String message, int lineNumber) {
		Warning warning = new Warning(message, lineNumber);
		warnings.add(warning);
	}
	
	public Type getType(String variable, int line) {
		if (!variableExists(variable)) {
	        addWarning("The variable: " + variable + " is not defined", line);
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
