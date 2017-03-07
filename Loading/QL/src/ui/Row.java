package ui;

import ast.type.Type;
import javafx.scene.control.Control;
import ui.field.Field;
import value.Value;
import ui.Notifier;

public class Row {

	private String name;
	private String label;
	private Type type;
	private Field entryField;

	public Row(String name, String label, Type type, Notifier notifier, Value answer) {
		this.name = name;
		this.label = label;
		this.type = type;
		// TODO move one level up?
		this.entryField = type.getField(name, notifier, answer);
	}

	// TODO default return statement
	public Value getAnswer() {
		return entryField.getAnswer();
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

	public Control getControl() {
		return entryField.getField();
	}

}
