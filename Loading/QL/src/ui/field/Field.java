package ui.field;

import javafx.scene.control.Control;
import value.Value;


public interface Field {
	
	public Value getAnswer();
	
	public void setAnswer(Value value);
	
	public Control getField();
	
}
