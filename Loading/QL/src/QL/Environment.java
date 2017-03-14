package QL;

import java.util.HashMap;
import java.util.Map;

import QL.ast.type.Type;
import QL.value.Value;

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

	public Value getAnswer(String variable) {
		if (isAnswered(variable)) {
			return variableAnswer.get(variable);
		}
		return getType(variable).getValue();
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
