package QL.ui;

import java.util.HashMap;
import java.util.Map;

import QL.ReferenceTable;
import QL.ast.type.Type;
import QL.value.Value;
import javafx.scene.control.Label;

public class Environment {

	private final Map<String, Value> variableAnswer;
	private final ReferenceTable variableType;
	private final StyleTable styleTable;
	
	public Environment(ReferenceTable variableType) {
		this.variableAnswer = new HashMap<>(); 
		this.variableType = variableType;
		this.styleTable = new StyleTable(variableType);
	}
	
	public Environment(ReferenceTable variableType, StyleTable styleTable) {
		this.variableAnswer = new HashMap<>(); 
		this.variableType = variableType;
		this.styleTable = styleTable;
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
		
		return variableType.getType(variable);
		
	}
	
	public void applyStyle(String variable, Label label) {
		
		styleTable.applyStyle(variable, label);			
	}
	

}
