package ui.field;

import javafx.scene.control.Control;
import ui.Questionnaire.Notifier;
import value.Value;


public interface Field {
	
	public Value getAnswer();
	
	public void setAnswer(Value value);
	
	public void addListener(Notifier listener);
	
	public Control getField();
	
}
