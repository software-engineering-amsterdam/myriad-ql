package ql.ui.field;

import ql.ui.Notifier;
import ql.value.IntegerValue;
import ql.value.Value;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class SpinBoxF implements Field {
	
	private final Spinner<Integer> field;
	
    public SpinBoxF(String name, Notifier notifier, IntegerValue value) {
		
		this.field = new Spinner<>();
		field.setId(name);
		
        final int initialValue = value.getValue();
        
        SpinnerValueFactory<Integer> valueFactory = 
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);
 
        field.setValueFactory(valueFactory);
		
		field.valueProperty().addListener(
				(observable, oldValue, newValue) -> notifier.updateQuestionnaire(name, new IntegerValue(newValue)));
	}
	
	@Override
	public Value getAnswer() {
		return new IntegerValue(field.getValue());
	}
	
	
	@Override
	public Spinner<Integer> getField() {
		return field;
	}

	@Override
	public void setValue(Value value) {
		field.getValueFactory().setValue(((IntegerValue) value).getValue());
		
	}
	
}
