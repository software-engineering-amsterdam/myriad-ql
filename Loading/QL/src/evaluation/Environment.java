package evaluation;

import java.util.HashMap;
import java.util.Map;

import ast.atom.Atom;

public class Environment {

	private Map<String, Atom> variableAnswer; // TODO remove answers
	
	public Environment() {
		variableAnswer = new HashMap<>(); 
	}
	
	public void addAnswer(String variable, Atom answer) {
		variableAnswer.put(variable, answer);
	}
		
	public Map<String, Atom> getAnswers() {
		return variableAnswer;
	}	
	
	public boolean isAnswered(String variable) {
		return variableAnswer.containsKey(variable);
	}
}
