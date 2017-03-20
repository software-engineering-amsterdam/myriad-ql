package QL.ui;

import QL.ui.field.Field;
import QL.value.Value;
import javafx.scene.control.Control;

class Row {

	private final String name;
	private final String label;
	private final Field field;

	public Row(String name, String label, Field field) {
		this.name = name;
		this.label = label;
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
