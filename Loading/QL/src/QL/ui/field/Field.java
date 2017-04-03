package QL.ui.field;

import QL.value.Value;
import javafx.scene.control.Control;


public interface Field {
	
	Value getAnswer();
	
	void setValue(Value value);
	
	Control getField();
	
}
