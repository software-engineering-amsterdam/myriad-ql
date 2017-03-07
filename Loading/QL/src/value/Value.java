package value;

import value.Value;
import value.BoolValue;

public abstract class Value {
		
	public abstract boolean isSet();
	public abstract String convertToString();
	
	// Binary Operators
	public Value add(Value other) { return null; }
	public Value sub(Value other) { return null; }
	public Value mul(Value other) { return null; }
	public Value div(Value other) { return null; }

	public BoolValue and(Value other) { return null; }
	public BoolValue or(Value other) { return null; }
	public BoolValue eq(Value other) { return null; }
	public BoolValue notEq(Value other) { return null; }
	public BoolValue greaterEq(Value other) { return null; }
	public BoolValue greater(Value other) { return null; }
	public BoolValue lessEq(Value other) { return null; }
	public BoolValue less(Value other) { return null; }
	
	// Unary Operators
	public Value plus() { return null; }
	public Value min() { return null; }
	public BoolValue not() { return null; }

}
