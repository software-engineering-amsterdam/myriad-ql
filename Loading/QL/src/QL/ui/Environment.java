package QL.ui;

import QL.ReferenceTable;
import QL.ast.type.Type;
import QL.ui.field.Field;
import QL.value.Value;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	
	private final Map<String, Value> answers;
	private final ReferenceTable references;
	private final StyleTable styleTable;
	// TODO change name
	private final Map<String, Field> fieldTable;
	
	public Environment(ReferenceTable references) {
		this.answers = new HashMap<>(); 
		this.references = references;
		this.styleTable = new StyleTable();
		this.fieldTable = new HashMap<>();
    }
	
	public void addDefaults() {
		styleTable.addDefaults(references);
	}
	
	public void addAnswer(String variable, Value answer) {
		answers.put(variable, answer);
	}

	public Value getAnswer(String variable) {
		return answers.get(variable);
	}

	public boolean isAnswered(String variable) {
		return answers.containsKey(variable);
	}
	
	public boolean isStyled(String variable) {
		return styleTable.isStyled(variable) && fieldTable.containsKey(variable);
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
	
	public void add(String variable, Field field) {
		fieldTable.put(variable, field);
	}

	public void add(String variable, Style style) {
		styleTable.add(variable, style);
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
