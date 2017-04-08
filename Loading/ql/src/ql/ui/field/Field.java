package ql.ui.field;

import ql.value.Value;
import javafx.scene.layout.GridPane;


public interface Field {
	
	Value getAnswer();
	
	void setValue(Value value);
	
	void draw(GridPane grid, int index);
}
