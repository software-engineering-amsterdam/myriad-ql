package ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import ui.Notifier;
import value.StringValue;
import value.Value;

public class Text implements Field {
	
	private TextField field;
	
	public Text(String name, Notifier notifier) {
		this.field = new TextField();
		
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
	public void setAnswer(Value value) {
		field.setText(((StringValue) value).getValue()); // TODO implicit you have to know to ask for a string
  	  	field.end();
	}
	
	@Override
	public TextField getField() {
		return field;
	}

}
