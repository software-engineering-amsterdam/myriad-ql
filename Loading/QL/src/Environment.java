import java.util.HashMap;
import java.util.Map;

import ast.atom.Atom;
import ast.type.Type;

public class Environment {
	
	// Or should only the answers be part of the environment
	Map<String, Atom> variableAnswer; // Should this be label - anwer as label is unique?
	Map<String, Type> variableType; // Should these two be combined to label - variable/type
	Map<String, String> labelVariable;
	
	Environment() {
		variableAnswer = new HashMap<>(); 
		variableType = new HashMap<>(); 
		labelVariable = new HashMap<>();
	}
	
	public void addLabel(String label, String variableName) {
		labelVariable.put(label, variableName);
	}
	
	public void addAnswer(String variable, Atom answer) {
		variableAnswer.put(variable, answer);
	}
	
	public void addVariableType(String variable, Type type) {
		variableType.put(variable, type);
	}
	
	public boolean variableExists(String variable) {
		return variableType.containsKey(variable);
	}
	
	public Map<String, Atom> getAnswers() {
		return variableAnswer;
	}
	
}
