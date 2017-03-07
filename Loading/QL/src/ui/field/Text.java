package ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import ui.Notifier;
import value.StringValue;
import value.Value;

public class Text implements Field {
	
	private TextField field;
	
	public Text(String name, Notifier notifier, StringValue value) {
		this.field = new TextField();
		
		if (value.isSet()) {
			field.setText(value.getValue());
		}
		
		field.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
            	notifier.updateQuestionnaire(name, new StringValue(newValue));
            }
    	});
	}
	
	@Override
	public Value getAnswer() {
		if (field.getText().isEmpty()) {
			return new StringValue();
		}	
		return new StringValue(field.getText());
	}
	
	
	@Override
	public TextField getField() {
		return field;
	}

}
