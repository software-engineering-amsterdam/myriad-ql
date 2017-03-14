package QL.value;

public class StringValue extends Value {

	private final String value;

    public StringValue(String value) {
        this.value = value;
    }
    
    public StringValue() {
    	this.value = null;
    }
    
    @Override
    public boolean isSet() {
    	return value != null;
    }

    @Override
	public BoolValue eq(Value other) {
    	
    	if (!isSet() || !other.isSet()) {
    		return new BoolValue();
    	}

    	return new BoolValue(value.equals(((StringValue) other).getValue()) );
	}

	@Override
	public BoolValue notEq(Value other) {
		
		
    	if (!isSet() || !other.isSet()) {
    		return new BoolValue();
    	}

		return new BoolValue(!value.equals(((StringValue) other).getValue()) );
	}
		
	public String getValue() {
		return this.value;
	}

	public String convertToString() {
    	return this.value;
    }
}
