package QL.ui.field;

import QL.ui.Notifier;
import QL.value.BoolValue;
import QL.value.Value;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class RadioB implements Field {
	
	private final RadioButton field;
	
	public RadioB(String name, String trueText, String falseText, Notifier notifier, BoolValue value) {
		
		final ToggleGroup group = new ToggleGroup();
		
		this.field = new RadioButton(trueText);
		field.setToggleGroup(group);

		RadioButton rb2 = new RadioButton(falseText);
		rb2.setToggleGroup(group);
		
		field.setId(name);
		
		field.setSelected(value.getValue());
		rb2.setSelected(!value.getValue());
		
		field.selectedProperty().addListener(
				(observable, oldValue, newValue) -> notifier.updateQuestionnaire(name, new BoolValue(newValue)));
	}
	
	@Override
	public Value getAnswer() {
		return new BoolValue(field.isSelected());
	}
	
	
	@Override
	public RadioButton getField() {
		return field;
	}

	@Override
	public void setValue(Value value) {
		// TODO Auto-generated method stub
		
	}
}
