package QL.ui;

import QL.ReferenceTable;
import QL.ast.type.Type;
import QL.value.Value;

import java.util.HashMap;
import java.util.Map;

public class Environment {

	private final Map<String, Value> variableAnswer;
	private final ReferenceTable variableType;
	
	public Environment(ReferenceTable variableType) {
		this.variableAnswer = new HashMap<>(); 
		this.variableType = variableType;
	}
	
	public void addAnswer(String variable, Value answer) {
		variableAnswer.put(variable, answer);
	}

	public Value getAnswer(String variable) {
		
		if (!variableAnswer.containsKey(variable)) {
			throw new AssertionError("The answer of variable " + variable + " is requested, " +
					"but is not answered");
		}
		
		return variableAnswer.get(variable);
	}

	public boolean isAnswered(String variable) {
		return variableAnswer.containsKey(variable);
	}
	
	public Type getType(String variable) {
		
		if (!variableType.variableExists(variable)) {
			throw new AssertionError("The variable " + variable + " is evaluated, " +
					"but not checked by the typechecker");
		}
		// TODO int line 0 does not make sense
		return variableType.getType(variable, 0);
		
	}
}
