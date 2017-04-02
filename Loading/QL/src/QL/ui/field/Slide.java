package QL.ui.field;

import QL.ui.Notifier;
import QL.value.IntegerValue;
import QL.value.Value;
import javafx.scene.control.Slider;

public class Slide implements Field {
	
	private final Slider field;
	
    public Slide(String name, Notifier notifier, IntegerValue value) {
		
		this.field = new Slider(0, 5, value.getValue());
		field.setId(name);
        
        field.setShowTickLabels(true);
        field.setMajorTickUnit(1);
        field.setBlockIncrement(1);
		
        field.valueProperty().addListener((obs, oldValue, newValue) -> { 
        	field.setValue(newValue.intValue()); 
        	notifier.updateQuestionnaire(name, new IntegerValue(newValue.intValue())); 
        });
	}
	
	@Override
	public Value getAnswer() {
		return new IntegerValue((int) field.getValue());
	}
	
	
	@Override
	public Slider getField() {
		return field;
	}
}
