package QL.ui.field;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import QL.ui.Notifier;
import QL.value.IntegerValue;
import QL.value.Value;

public class Number implements Field {
	
	private final TextField field;
	
	public Number(String name, Notifier notifier, IntegerValue value) {
		this.field = new TextField();
		
		field.setId(name);
		
		if (value.isSet()) {
			String valueText = Integer.toString(value.getValue());
			field.setText(valueText);
			field.positionCaret(valueText.length());
		}
		
    	field.textProperty().addListener(new ChangeListener<String>() {
	      @Override
	      public void changed(ObservableValue<? extends String> observable, 
	      				    String oldValue, String newValue) {

	          if (!newValue.matches("\\d*")) {
	              field.setText(newValue.replaceAll("[^\\d]", ""));
	          } else if (!newValue.isEmpty()) {
	        	  notifier.updateQuestionnaire(name, new IntegerValue(Integer.parseInt(newValue)));
	          } else {
	        	  notifier.updateQuestionnaire(name, new IntegerValue());
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
