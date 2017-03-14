package QL.value;

public class BoolValue extends Value {

	private final Boolean value;

    public BoolValue(Boolean value) {
    	this.value = value;
    }

    public BoolValue() {
    	this.value = false;
    }

	@Override
    public BoolValue and(Value other) {
		
    	return new BoolValue(value && ((BoolValue) other).getValue());
    }

	@Override
	public BoolValue or(Value other) {
		
		return new BoolValue(value || ((BoolValue) other).getValue());
	}

	@Override
	public BoolValue eq(Value other) {

		return new BoolValue(value.equals(((BoolValue) other).getValue()));
	}

	@Override
	public BoolValue notEq(Value other) {

    	return new BoolValue(value != ((BoolValue) other).getValue());
	}

	@Override
	public BoolValue not() {
		
		return new BoolValue(!value);
	}

    public Boolean getValue() {
        return this.value;
    }

    public String convertToString() {
    	return this.value.toString();
	}
}
