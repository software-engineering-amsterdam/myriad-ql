package ui.field;

import javafx.scene.control.TextField;
import ui.Questionnaire.Notifier;
import value.EmptyValue;
import value.IntegerValue;
import value.StringValue;
import value.Value;

import java.util.function.Function;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Number implements Field {
	
	private Notifier listener;
	private TextField field;
	
	public Number(String name) {
		this.field = new TextField();
		
    	field.textProperty().addListener(new ChangeListener<String>() {
	      @Override
	      public void changed(ObservableValue<? extends String> observable, 
	      				    String oldValue, String newValue) {
	          if (!newValue.matches("\\d*")) {
	              field.setText(newValue.replaceAll("[^\\d]", ""));
	          } else {
	        	  listener.updateQuestionnaire(name, new StringValue(newValue));
	          }
	      }
    	});
	}

	@Override
	public Value getAnswer() {
		String str = field.getText();
		if (str.isEmpty()) {
			return new EmptyValue();
		}
		return new IntegerValue(Integer.valueOf(str));
	}

	@Override
	public void setAnswer(Value value) {
		field.setText(Integer.toString(value.getValue().getNumber())); // TODO improve
		
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
