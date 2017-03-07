package QL.value;

public class BoolValue extends Value {

	private final Boolean value;

    public BoolValue(Boolean value) {
    	this.value = value;
    }

    // TODO default false ?
    public BoolValue() {
    	this.value = false;
    }
    
    @Override
    public boolean isSet() {
    	return value != null;
    }

	@Override
    public BoolValue and(Value other) {
		
    	if (!isSet() || !other.isSet()) {
    		return new BoolValue();
    	}
		
    	return new BoolValue(value && ((BoolValue) other).getValue());
    }

	@Override
	public BoolValue or(Value other) {
		
    	if (!isSet() || !other.isSet()) {
    		return new BoolValue();
    	}
		
		return new BoolValue(value || ((BoolValue) other).getValue());
	}

	@Override
	public BoolValue eq(Value other) {
		
		if (!isSet() || !other.isSet()) {
    		return new BoolValue();
    	}
		System.out.println("TODO bool eq Does this work?");
		 return new BoolValue(value.equals(((BoolValue) other).getValue()));
	}

	@Override
	public BoolValue notEq(Value other) {
		
		if (!isSet() || !other.isSet()) {
    		return new BoolValue();
    	}
		System.out.println("TODO Does this work?");
//		return new BoolValue(this.equals(other));
		 return new BoolValue(value != ((BoolValue) other).getValue());
	}

	@Override
	public BoolValue not() {
		
    	if (isSet()) {
    		return new BoolValue();
    	}
		
		return new BoolValue(!value);
	}

    public Boolean getValue() {
        return this.value;
    }

    public String convertToString() {
    	return this.value.toString();
	}
}
