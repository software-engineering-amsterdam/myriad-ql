package QL.ui.field;

import QL.ui.Notifier;
import QL.value.BoolValue;
import QL.value.Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class DropDownF implements Field {

	private final ComboBox<String> field;
	private String trueText;
	private String falseText;

	public DropDownF(String name, String trueText, String falseText, Notifier notifier, BoolValue value) {				
				
		this.trueText = trueText;
		this.falseText = falseText;
		
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        trueText,
			        falseText
			    );
		this.field = new ComboBox<String>(options);
		
		field.setId(name);
		
	    field.valueProperty().addListener((observable, oldValue, newValue) -> {	    	
            notifier.updateQuestionnaire(name, new BoolValue(currentValue(newValue)));
	    });
		
	}
	
	private boolean currentValue(String selectedText) {
		return selectedText.equals(trueText);
	}
	
	@Override
	public Value getAnswer() {
		return new BoolValue(currentValue(field.getValue()));
	}
	
	
	@Override
	public ComboBox<String> getField() {
		return field;
	}

	@Override
	public void setValue(Value value) {
		
		if (((BoolValue) value).getValue()) {
			field.setValue(trueText);
		} else {
			field.setValue(falseText);	
		}	
	}
}
