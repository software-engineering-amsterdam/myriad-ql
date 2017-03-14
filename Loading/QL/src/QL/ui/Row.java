package QL.ui;

import QL.ast.type.Type;
import javafx.scene.control.Control;
import QL.ui.field.Field;
import QL.value.Value;

class Row {

	private final String name;
	private final String label;
	private final Type type;
	private final Field field;

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

	public Control getControl() {
		return field.getField();
	}

}
