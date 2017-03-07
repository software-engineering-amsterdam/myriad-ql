package ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import ui.Notifier;
import value.IntegerValue;
import value.Value;

public class Number implements Field {
	
	private TextField field;
	// private IntegerValue value;
	
	public Number(String name, Notifier notifier, IntegerValue value) {
		this.field = new TextField();
		
		if (value.isSet()) {
			field.setText(Integer.toString(value.getValue()));
		}
		
    	field.textProperty().addListener(new ChangeListener<String>() {
	      @Override
	      public void changed(ObservableValue<? extends String> observable, 
	      				    String oldValue, String newValue) {
	          if (!newValue.matches("\\d*")) {
	              field.setText(newValue.replaceAll("[^\\d]", ""));
	          } else if (!newValue.isEmpty()) {
	        	  notifier.updateQuestionnaire(name, new IntegerValue(Integer.parseInt(newValue)));

	          }
	      }
    	});
	}

	@Override
	public Value getAnswer() {
		String str = field.getText();
		if (str.isEmpty()) {
			return new IntegerValue();
		}
		return new IntegerValue(Integer.valueOf(str));
	}
	
	@Override 
	public TextField getField() {
		return field;
	}
	
}
