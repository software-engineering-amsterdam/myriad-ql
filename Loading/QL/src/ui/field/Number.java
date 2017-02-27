package ui.field;

import javafx.scene.control.TextField;
import value.EmptyValue;
import value.IntegerValue;
import value.Value;

import java.util.function.Function;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Number extends TextField implements Field {
	
	public Number() {
		// this.field = new TextField();
    	textProperty().addListener(new ChangeListener<String>() {
	      @Override
	      public void changed(ObservableValue<? extends String> observable, 
	      				    String oldValue, String newValue) {
	          if (!newValue.matches("\\d*")) {
	              setText(newValue.replaceAll("[^\\d]", ""));
	          }
	      }
    	});
	}

	@Override
	public Value getAnswer() {
		String str = getText();
		if (str.isEmpty()) {
			return new EmptyValue();
		}
		return new IntegerValue(Integer.valueOf(str));
	}

	@Override
	public void setAnswer(Value value) {
		setText((String) value.getValue());
		
	}
	
}
