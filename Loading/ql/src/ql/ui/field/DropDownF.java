package ql.ui.field;

import ql.ui.Notifier;
import ql.value.BoolValue;
import ql.value.Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

public class DropDownF implements Field {

	private final ComboBox<String> field;
	private final String trueText;
	private final String falseText;

	public DropDownF(String name, String trueText, String falseText, Notifier notifier, BoolValue value) {				
				
		this.trueText = trueText;
		this.falseText = falseText;
		
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        trueText,
			        falseText
			    );
		this.field = new ComboBox<>(options);
		
		field.setId(name);
		
	    field.valueProperty().addListener((observable, oldValue, newValue) -> notifier.updateQuestionnaire(name, new BoolValue(currentValue(newValue))));
		
	}
	
	private boolean currentValue(String selectedText) {
		return selectedText.equals(trueText);
	}
	
	@Override
	public Value getAnswer() {
		return new BoolValue(currentValue(field.getValue()));
	}

	@Override
	public void setValue(Value value) {
		
		if (((BoolValue) value).getValue()) {
			field.setValue(trueText);
		} else {
			field.setValue(falseText);	
		}	
	}
	
	@Override
	public void draw(GridPane grid, int index) {
		grid.add(field, 1, index);	
	}
}
