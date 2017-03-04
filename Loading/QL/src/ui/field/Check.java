package ui.field;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ast.type.BooleanType;
import ast.type.Type;
// import javafx.scene.control.Control;
import javafx.scene.control.Control;
import ui.Questionnaire.Notifier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import value.BoolValue;
import value.StringValue;
import value.Value;

public class Check implements Field {

	private Notifier listener;
	private CheckBox field;
	private String name;
	private boolean value; // TODO value is not saved
	// ad this CheckBox is created over and over again
	
	public Check(String name) {
		
		this.field = new CheckBox();
		this.name = name;
		this.value = false;
		
		field.selectedProperty().addListener(new ChangeListener<Boolean>()  {
	           
			@Override
           public void changed(ObservableValue<? extends Boolean> observable,
                               Boolean oldValue, Boolean newValue) {
				
//				if (!newValue.equals(value)) {
//					value = newValue;
					listener.updateQuestionnaire(name, new BoolValue(name, newValue));
				//}
	       		
            }
		});
	}
	
	@Override
	public void addListener(Notifier listener) {
		this.listener = listener;
	}
	
	@Override
	public Value getAnswer() {
		return new BoolValue(name, field.isSelected());
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
