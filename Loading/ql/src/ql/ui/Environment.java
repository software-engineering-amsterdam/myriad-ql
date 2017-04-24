package ql.ui;

import ql.ReferenceTable;
import ql.ast.type.Type;
import ql.ui.field.Field;
import ql.value.Value;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	
	private final Map<String, Value> answers;
	private final ReferenceTable references;
	private final StyleTable styles;
	private final Map<String, Field> fields;
	
	public Environment(ReferenceTable references) {
		this.answers = new HashMap<>();
		this.references = references;
		this.styles = new StyleTable();
		this.fields = new HashMap<>();
    }
	
	void addDefaults() {
		styles.addDefaults(references);
	}
	
	void addAnswer(String name, Value answer) {
		answers.put(name, answer);
	}

	public Value getAnswer(String name) {
		return answers.get(name);
	}

	public boolean isAnswered(String name) {
		return answers.containsKey(name);
	}
	
	public boolean isStyled(String name) {
		return styles.isStyled(name) && fields.containsKey(name);
	}
	
	public Type getType(String name) {
		
		if (!references.nameExists(name)) {
			throw new AssertionError("The question " + name + " is evaluated, " +
					"but not checked by the typechecker");
		}
		
		return references.getType(name);
	}

	void applyStyle(String name, Label label) {
		styles.applyStyle(name, label);
	}

	public void addStyle(String name, Style style) {
		styles.add(name, style);
	}

	public void addField(String name, Field field) {
		fields.put(name, field);
	}

	public Field getField(String name) {
		return fields.get(name);
	}
}
