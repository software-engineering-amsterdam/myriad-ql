package evaluation;

import java.util.HashMap;
import java.util.Map;

import ast.type.Type;
import value.Value;

// TODO move one level higher
public class Environment {

	private Map<String, Value> variableAnswer;
	private Map<String, Type> variableType;
	
	public Environment(Map<String, Type> variableType) {
		this.variableAnswer = new HashMap<>(); 
		this.variableType = variableType;
	}
	
	public void addAnswer(String variable, Value answer) {
		variableAnswer.put(variable, answer);
	}

	public Map<String, Value> getAnswers() {
		return variableAnswer;
	}

	public Value getAnswer(String variable) {
		if (isAnswered(variable)) {
			return variableAnswer.get(variable);
		}
		return null; // TODO throw here?
	}
	
	public Type getType(String variable) {
		if (!variableType.containsKey(variable)) {
			throw new AssertionError("The variable " + variable + " is evaluated, " +
					"but not checked by the typechecker");
		}
		return variableType.get(variable);
		
	}
	
	public boolean isAnswered(String variable) {
		return variableAnswer.containsKey(variable);
	}
}
