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
	
	void addDefaults() {
		styleTable.addDefaults(references);
	}
	
	public void addAnswer(String name, Value answer) {
		answers.put(name, answer);
	}

	public Value getAnswer(String name) {
		return answers.get(name);
	}

	public boolean isAnswered(String name) {
		return answers.containsKey(name);
	}
	
	public boolean isStyled(String name) {
		return styleTable.isStyled(name) && fieldTable.containsKey(name);
	}
	
	public Type getType(String name) {
		
		if (!references.nameExists(name)) {
			throw new AssertionError("The question " + name + " is evaluated, " +
					"but not checked by the typechecker");
		}
		
		return references.getType(name);
	}

	void applyStyle(String name, Label label) {
		styleTable.applyStyle(name, label);
	}

	public void addStyle(String name, Style style) {
		styleTable.add(name, style);
	}

	public void addField(String name, Field field) {
		fieldTable.put(name, field);
	}

	public Field getField(String name) {
		return fieldTable.get(name);
	}
}
