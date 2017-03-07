package ui;

import ast.type.Type;
import javafx.scene.control.Control;
import ui.field.Field;
import value.Value;
import ui.Questionnaire.Notifier;

public class Row {

	private String name;
	private String label;
	private Type type;
	private Field entryField;

	public Row(String name, String label, Type type) {
		this.name = name;
		this.label = label;
		this.type = type;
		this.entryField = type.getField(name);
	}

	// TODO default return statement
	public Value getAnswer() {
		return entryField.getAnswer();
	}

	public void setAnswer(Value value) {
		entryField.setAnswer(value);
	}

	public String getName() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public Type getType() {
		return type;
	}
	
	public void addListener(Notifier listener) {
		entryField.addListener(listener);
	}

	public Control getControl() {
		return entryField.getField();
	}

}
