package value;

import value.Value;
import value.BoolValue;

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
    	
    	if (isSet() || other.isSet()) {
    		return new BoolValue(0);
    	}
    	System.out.println("TODO does this work?");
    	return new BoolValue (this.equals(other));
    	
		// return new BoolValue(value.equals(other.getValue()) );
	}

	@Override
	public BoolValue notEq(Value other) {
		
		
    	if (isSet() || other.isSet()) {
    		return new BoolValue(0);
    	}
    	
    	System.out.println("TODO does this work?");
    	return new BoolValue (!this.equals(other));
		
		// return new BoolValue(!value.equals(other.getValue()) );
	}
		
	public String getValue() {
		return value;
	}
}
