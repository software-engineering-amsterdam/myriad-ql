package QL.value;

public class IntegerValue extends Value {

	private final Integer value;
	
    public IntegerValue(Integer value) {
        this.value = value;
    }
    
    public IntegerValue() {
    	this.value = null;
    }
    
    @Override
    public boolean isSet() {
    	return value != null;
    }
    
	@Override
	public Value add(Value other) {
		
		if (!isSet() || !other.isSet()) {
			return new IntegerValue();
		}
		
		return new IntegerValue(value + ((IntegerValue) other).getValue() );
	}
	
	@Override
	public Value sub(Value other) {
		
		if (!isSet() || !other.isSet()) {
			return new IntegerValue();
		}
		
		return new IntegerValue(value - ((IntegerValue) other).getValue() );
	}
	
	@Override
	public Value mul(Value other) {
		
		if (!isSet() || !other.isSet()) {
			return new IntegerValue();
		}
		
		return new IntegerValue(value * ((IntegerValue) other).getValue() );
	}

	@Override
	public Value div(Value other) {

		if (!isSet() || !other.isSet()) {
			return new IntegerValue();
		}

		if (((IntegerValue) other).getValue() == 0) {
			return new IntegerValue(0);
		}
		
		return new IntegerValue(value / ((IntegerValue) other).getValue() );
	}

	@Override
	public BoolValue eq(Value other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolValue();
		}

		return new BoolValue(value.equals(((IntegerValue) other).getValue()) );
	}

	@Override
	public BoolValue notEq(Value other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolValue();
		}

		return new BoolValue(!value.equals(((IntegerValue) other).getValue()) );
	}
	
	@Override
	public BoolValue greaterEq(Value other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolValue();
		}
		
		return new BoolValue(value >= ((IntegerValue) other).getValue() );
	}

	@Override
	public BoolValue greater(Value other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolValue();
		}
		
		return new BoolValue(value > ((IntegerValue) other).getValue() );
	}

	@Override
	public BoolValue lessEq(Value other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolValue();
		}
		
		return new BoolValue(value <= ((IntegerValue) other).getValue() );
	}

	@Override
	public BoolValue less(Value other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolValue();
		}
		
		return new BoolValue(value < ((IntegerValue) other).getValue() );
	}
	
	@Override
	public Value plus() {
		
		if (!isSet()) {
			return new IntegerValue();
		}
		
		return new IntegerValue(Math.abs(value));
	}

	@Override
	public Value min() {
		
		if (!isSet()) {
			return new IntegerValue();
		}
		
		return new IntegerValue(- value );
	}

    public Integer getValue() {
        return this.value;
    }

    public String convertToString() {
    	return this.value.toString();
	}
	
}
