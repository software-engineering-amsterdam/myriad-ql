package ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import ui.Notifier;
import value.BoolValue;
import value.Value;

public class Check implements Field {

	private CheckBox field;
	
	public Check(String name, Notifier notifier, BoolValue value) {
		
		this.field = new CheckBox();
		field.setSelected(value.getValue());
		
		field.selectedProperty().addListener(new ChangeListener<Boolean>()  {
	           
			@Override
           public void changed(ObservableValue<? extends Boolean> observable,
                               Boolean oldValue, Boolean newValue) {
				
				notifier.updateQuestionnaire(name, new BoolValue(newValue));
            }
		});
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
