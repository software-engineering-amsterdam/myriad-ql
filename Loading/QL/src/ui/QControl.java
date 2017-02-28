package ui;

import java.util.function.Function;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import ui.field.Field;
import value.Value;

public class QControl {
	
	private Field field;
	
	public QControl(Field field) {
		this.field = field;
	}
	
	public void setAnswer(Value value) {
		field.setAnswer(value);
	}
	
	public Value getAnswer() {
		return field.getAnswer();
	}
	
	// TODO remove
	public Field getField() {
		return field;
	}
}
