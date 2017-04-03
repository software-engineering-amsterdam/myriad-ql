package ql.ui.field;

import ql.value.Value;
import javafx.scene.control.Control;


public interface Field {
	
	Value getAnswer();
	
	void setValue(Value value);
	
	Control getField();
	
}
