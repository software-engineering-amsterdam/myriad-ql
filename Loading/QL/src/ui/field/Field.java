package ui.field;

import ui.Notifier;
import value.Value;


public interface Field {
	
	public Value getAnswer();
	
	public void setAnswer(Value value);
	
	public void addListener(Notifier listener);
	
}
