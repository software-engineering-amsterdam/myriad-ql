package ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import ui.Notifier;
import value.StringValue;
import value.Value;

public class Text implements Field {
	
	private Notifier listener;
	private TextField field;
	
	public Text(String name) {
		this.field = new TextField();
		
		field.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
            	listener.updateQuestionnaire(name, new StringValue(newValue));
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
	public void addListener(Notifier listener) {
		this.listener = listener;	
	}
	
	@Override
	public TextField getField() {
		return field;
	}

}
