package ui.field;

import java.util.function.Function;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import ui.Notifier;
import value.BoolValue;
import value.EmptyValue;
import value.StringValue;
import value.Value;

// TODO do not extend the standard library
public class Text extends TextField implements Field {
	
	private Notifier listener;
	private String name;
	
	public Text(String name) {
		super();
		this.name = name;
		
		textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
            	
            	listener.updateQuestionnaire(name, new StringValue(newValue));
            }
    	});
	}
	
	@Override
	public Value getAnswer() {
		if (getText().isEmpty()) {
			return new EmptyValue();
		}	
		return new StringValue(getText());
	}
	
	
	@Override
	public void setAnswer(Value value) {
		setText((String) value.getValue());	
	}


	@Override
	public void addListener(Notifier listener) {
		this.listener = listener;	
	}

}
