package ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import ui.Questionnaire.Notifier;
import value.Value;

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
	          } else if (!newValue.isEmpty()) {
	        	  listener.updateQuestionnaire(name, new Value(Integer.parseInt(newValue)));

	          }
	      }
    	});
	}

	@Override
	public Value getAnswer() {
		String str = field.getText();
		if (str.isEmpty()) {
			return new Value();
		}
		return new Value(Integer.valueOf(str));
	}

	@Override
	public void setAnswer(Value value) {
		if (value.getValue().getNumber() != null) {
			field.setText(Integer.toString(value.getValue().getNumber())); // TODO getValue, getNumber : move number etc to value
      	  	field.end();
		}
		
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
