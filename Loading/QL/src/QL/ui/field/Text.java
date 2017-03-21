package QL.ui.field;

import QL.ui.Notifier;
import QL.value.StringValue;
import QL.value.Value;
import javafx.scene.control.TextField;

public class Text implements Field {
	
	private final TextField field;
	
	public Text(String name, Notifier notifier, StringValue value) {
		this.field = new TextField();
		
		field.setId(name);
		field.setText(value.getValue());
		field.positionCaret(value.getValue().length());
		
		field.textProperty().addListener(
				(observable, oldValue, newValue) -> notifier.updateQuestionnaire(name, new StringValue(newValue)));
	}
	
	@Override
	public Value getAnswer() {
		if (field.getText().isEmpty()) {
			return new StringValue();
		}	
		return new StringValue(field.getText());
	}
	
	
	@Override
	public TextField getField() {
		return field;
	}
	

}
