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
		
		if (labelVariable.containsKey(label)) {
			System.out.println("The question \" "  + label 
					+ " \"on line ... exists twice in the questionnaire.");
			// TODO print to the screen for the user
		}		
		labelVariable.put(label, variableName);
	}
	
	public void addAnswer(String variable, Atom answer) {
		
		// TODO check whether the answer has the correct type
		Type expectedType = variableType.get(variable);
		if (answer.getType() != expectedType.getType()) {
			
			System.out.println("The answer on the question: " + variable
					+ " should be of type " + expectedType.getType() + 
					" but is of type: " + answer.getType());
			// TODO print to the screen for the user
		}
		variableAnswer.put(variable, answer);
	}
	
	public void addVariableType(String variable, Type type) {
		
		if (variableType.containsKey(variable)) {
			// TODO THROW if we do not allow two times the same variable name
			// Also change the type of map in that case
		}
		variableType.put(variable, type);
	}
	
	public Map<String, Atom> getAnswers() {
		return variableAnswer;
	}
	
}
