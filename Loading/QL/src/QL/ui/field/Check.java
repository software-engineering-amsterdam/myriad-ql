package QL.ui.field;

import QL.ui.Notifier;
import QL.value.BoolValue;
import QL.value.Value;
import javafx.scene.control.CheckBox;

public class Check implements Field {

	private final CheckBox field;
	
	public Check(String name, Notifier notifier, BoolValue value) {
		
		this.field = new CheckBox();
		field.setId(name);
		
		field.setSelected(value.getValue());
		
		field.selectedProperty().addListener(
				(observable, oldValue, newValue) -> notifier.updateQuestionnaire(name, new BoolValue(newValue)));
	}
	
	@Override
	public Value getAnswer() {
		return new BoolValue(field.isSelected());
	}
	
	
	@Override
	public CheckBox getField() {
		return field;
	}

}
