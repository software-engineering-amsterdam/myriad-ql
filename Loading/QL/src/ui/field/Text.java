package ui.field;

import java.util.function.Function;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import value.EmptyValue;
import value.StringValue;
import value.Value;

public class Text extends TextField implements Field {
	
	private Boolean changed;
	
	public Text() {
////		// super(field);
		// this.changed = false;
		// setOnChange();
	}
//	
//	
//	@Override
//	public void getAnswer2(StringType type) {
//		
//	}
	

	
//	Text() {
//		this.field = new TextField();
//		this.changed = false;
//		setOnChange();
//	}
	
	
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
	 // public String setOnChange(Function<String, String> func) {
	public Boolean isChanged(Value oldAnswer) {
		
		changed = false;
		
		textProperty().addListener(new ChangeListener<String>()  {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
            	
            	if (newValue.equals(oldAnswer.getValue())) {
            		changed = true;
            		requestFocus();
            	}

//        		Value oldAnswer = answers.get(question.getName()); 
//            	if (oldAnswer == null || !newValue.equals(oldAnswer.getValue())) {
//            		answers.put(question.getName(), new StringValue(newValue));
//            		renderQuestionnaire(primaryStage, grid); // Only render when something actually changes in the form
//            		
//            	}
            	
            }
    	});
		
		return changed;
	}
		
	// }
//

}
