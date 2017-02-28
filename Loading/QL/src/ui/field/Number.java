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

public class Number extends TextField implements Field {
	
	private Notifier listener;
	private String name;
	
	public Number(String name) {
		super();
		this.name = name;
		
    	textProperty().addListener(new ChangeListener<String>() {
	      @Override
	      public void changed(ObservableValue<? extends String> observable, 
	      				    String oldValue, String newValue) {
	          if (!newValue.matches("\\d*")) {
	              setText(newValue.replaceAll("[^\\d]", ""));
	          } else {
	        	  listener.updateQuestionnaire(name, new StringValue(newValue));
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

	@Override
	public void addListener(Notifier listener) {
		this.listener = listener;	
	}
	
}
