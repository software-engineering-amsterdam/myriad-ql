package QL.ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import QL.ui.Notifier;
import QL.value.StringValue;
import QL.value.Value;

public class Text implements Field {
	
	private final TextField field;
	
	public Text(String name, Notifier notifier, StringValue value) {
		this.field = new TextField();
		
		field.setId(name);
		
		if (value.isSet()) {
			field.setText(value.getValue());
			field.positionCaret(value.getValue().length());
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
