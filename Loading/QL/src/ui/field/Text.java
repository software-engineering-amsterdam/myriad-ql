package ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import ui.Questionnaire.Notifier;
import value.EmptyValue;
import value.StringValue;
import value.Value;

public class Text implements Field {
	
	private Notifier listener;
	private TextField field;
	private String name;  // TODO should value not be a member instead?
	
	public Text(String name) {
		this.field = new TextField();
		this.name = name;
		
		field.textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
            	listener.updateQuestionnaire(name, new StringValue(name, newValue));
            }
    	});
	}
	
	@Override
	public Value getAnswer() {
		if (field.getText().isEmpty()) {
			return new EmptyValue();
		}	
		return new StringValue(name, field.getText());
	}
	
	
	@Override
	public void setAnswer(Value value) {
		field.setText(value.getValue().getString());
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
