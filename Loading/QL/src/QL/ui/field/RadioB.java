package QL.ui.field;

import QL.ui.Notifier;
import QL.value.BoolValue;
import QL.value.Value;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class RadioB implements Field {
	
	private final RadioButton field;
	
	public RadioB(String name, Notifier notifier, BoolValue value) {
		
		this.field = new RadioButton();
		
		final ToggleGroup group = new ToggleGroup();

		RadioButton rb1 = new RadioButton("Yes");
		rb1.setToggleGroup(group);


		RadioButton rb2 = new RadioButton("No");
		rb2.setToggleGroup(group);
		
		field.setId(name);
		
		// TODO refactor
		if (value.getValue()) {
			rb1.setSelected(true);
		} else {
			rb2.setSelected(true);
		}
		
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
}
