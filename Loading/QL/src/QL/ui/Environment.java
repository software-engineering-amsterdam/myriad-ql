package QL.ui;

import QL.ReferenceTable;
import QL.ast.type.Type;
import QL.ui.field.Field;
import QL.value.Value;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class Environment {

	private final Map<String, Value> variableAnswer;
	private final ReferenceTable references;
	private final StyleTable styleTable;
	private Map<String, Field> fieldTable;
	
	public Environment(ReferenceTable references) {
		this.variableAnswer = new HashMap<>(); 
		this.references = references;
		this.styleTable = new StyleTable(references);
		this.fieldTable = new HashMap<>();
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
		
		if (!references.variableExists(variable)) {
			throw new AssertionError("The variable " + variable + " is evaluated, " +
					"but not checked by the typechecker");
		}
		
		return references.getType(variable);

	}

	void applyStyle(String variable, Label label) {
		styleTable.applyStyle(variable, label);
	}


	void addField(String name, Field field) {
		fieldTable.put(name, field);
	}

	boolean hasEmptyFieldTable() {
		return fieldTable.isEmpty();
	}

	public Field getField(String variable) {
		return fieldTable.get(variable);
	}
}
