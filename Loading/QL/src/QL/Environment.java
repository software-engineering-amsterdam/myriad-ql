package QL;

import QL.ast.type.Type;
import QL.value.Value;

import java.util.HashMap;
import java.util.Map;

public class Environment {

	private final Map<String, Value> variableAnswer;
	private final Map<String, Type> variableType;
	
	public Environment(Map<String, Type> variableType) {
		this.variableAnswer = new HashMap<>(); 
		this.variableType = variableType;
	}
	
	public void addAnswer(String variable, Value answer) {
		variableAnswer.put(variable, answer);
	}

	public Value getAnswer(String variable) {
		
		// TODO how do we use asserts in our code?
		// or throw
		assert(variableAnswer.containsKey(variable));
		
		return variableAnswer.get(variable);
	}
	
	
	// TODO remove
	public boolean isAnswered(String variable) {
		return variableAnswer.containsKey(variable);
	}
	
	public Type getType(String variable) {
		if (!variableType.containsKey(variable)) {
			throw new AssertionError("The variable " + variable + " is evaluated, " +
					"but not checked by the typechecker");
		}
		return variableType.get(variable);
		
	}
}
