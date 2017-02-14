import java.util.HashMap;
import java.util.Map;

import ast.atom.Atom;
import ast.type.Type;

public class Environment {
	
	Map<String, Atom> answers;
	Map<String, Type> variableType;
	
	Environment() {
		answers = new HashMap<>();
		variableType = new HashMap<>(); // 
	}
	
	public void addAnswer(String question, Atom answer) {
		
		if (answers.containsKey(question)) {
			// TODO THROW you have a double question
		}		
		answers.put(question, answer);
	}
	
	public void addVariableType(String variable, Type type) {
		
		if (variableType.containsKey(variable)) {
			// TODO THROW if we do not allow two times the same variablename
			// Also change the type of map in that case
		}
		variableType.put(variable, type);
	}
	
	public Map<String, Atom> getAnswers() {
		return answers;
	}
	
}
