package ql.ui;

import ql.ui.field.Field;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

class Row {

	private final String name;
	private final Label label;
	private final Field field;

	public Row(String name, Label label, Field field) {
		this.name = name;
		this.label = label;
		this.field = field;
	}

	String getAnswer() {
		return field.getAnswer().convertToString();
	}

	String getName() {
		return name;
	}

	public Label getLabel() {
		return label;
	}
	
	void draw(GridPane grid, int index) {
		grid.add(label, 0, index);
		field.draw(grid, index);
	}

}
