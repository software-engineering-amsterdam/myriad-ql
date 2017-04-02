package QL.ui.field;

import QL.ui.Notifier;
import QL.value.IntegerValue;
import QL.value.Value;
import javafx.scene.control.Slider;

public class Slide implements Field {
	
	private final Slider field;
	
    public Slide(String name, Notifier notifier, IntegerValue value) {
		
		this.field = new Slider(0, 5, 1);
		field.setId(name);
        
        field.setShowTickMarks(true);
        field.setShowTickLabels(true);
        field.setMajorTickUnit(1);
        field.setMinorTickCount(1);
        field.setBlockIncrement(1);
		
		field.valueProperty().addListener(
				(observable, oldValue, newValue) -> notifier.updateQuestionnaire(name, new IntegerValue(newValue.intValue())));
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
