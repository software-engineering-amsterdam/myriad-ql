package QL.ui;

import QL.ast.type.Type;
import javafx.scene.control.Control;
import QL.ui.field.Field;
import QL.value.Value;

public class Row {

	private String name;
	private String label;
	private Type type;
	private Field field;

	public Row(String name, String label, Type type, Field field) {
		this.name = name;
		this.label = label;
		this.type = type;
		this.field = field;
	}

	public Value getAnswer() {
		return field.getAnswer();
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
		return field.getField();
	}

}
