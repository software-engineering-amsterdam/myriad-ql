package QL.ui;

import QL.ui.field.Field;
import QL.value.Value;
import javafx.scene.control.Control;
import javafx.scene.control.Label;

public class Row {

	private final String name;
	private final Label label;
	private final Field field;

	public Row(String name, Label label, Field field) {
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

	public Label getLabel() {
		return label;
	}

	public Control getControl() {
		return field.getField();
	}

}
