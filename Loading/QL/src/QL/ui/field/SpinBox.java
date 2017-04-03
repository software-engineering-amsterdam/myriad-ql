package QL.ui.field;

import QL.ui.Notifier;
import QL.value.BoolValue;
import QL.value.IntegerValue;
import QL.value.Value;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class SpinBox implements Field {
	
	private final Spinner<Integer> field;
	
    public SpinBox(String name, Notifier notifier, IntegerValue value) {
		
		this.field = new Spinner<Integer>();
		field.setId(name);
		
        final int initialValue = 3;
        
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
