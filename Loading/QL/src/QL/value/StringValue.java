package QL.value;

import QL.ui.Notifier;
import QL.ui.field.Field;
import QL.ui.field.Text;

public class StringValue extends Value {

	private final String value;

    public StringValue(String value) {
        this.value = value;
    }
    
    public StringValue() {
    	this.value = "";
    }

	@Override
	public Field getField(String name, Notifier notifier, Value value) {
		return new Text(name, notifier, (StringValue) value);
	}

	@Override
	public BoolValue eq(Value other) {
    	return new BoolValue(value.equals(((StringValue) other).getValue()) );
	}

	@Override
	public BoolValue notEq(Value other) {
		return new BoolValue(!value.equals(((StringValue) other).getValue()) );
	}
		
	public String getValue() {
		return this.value;
	}

	public String convertToString() {
    	return this.value;
    }
}
