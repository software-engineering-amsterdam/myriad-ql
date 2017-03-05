package ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import ui.Questionnaire.Notifier;
import value.BoolValue;
import value.Value;

public class Check implements Field {

	private Notifier listener;
	private CheckBox field;
	
	public Check(String name) {
		
		this.field = new CheckBox();
		
		field.selectedProperty().addListener(new ChangeListener<Boolean>()  {
	           
			@Override
           public void changed(ObservableValue<? extends Boolean> observable,
                               Boolean oldValue, Boolean newValue) {
				
				listener.updateQuestionnaire(name, new BoolValue(newValue));
            }
		});
	}
	
	@Override
	public void addListener(Notifier listener) {
		this.listener = listener;
	}
	
	@Override
	public Value getAnswer() {
		return new BoolValue(field.isSelected());
	}
	
	@Override
	public void setAnswer(Value value) {
		field.setSelected(value.getValue().getValue()); // TODO two times getValue()??	
	}
	
	@Override
	public CheckBox getField() {
		return field;
	}

}
