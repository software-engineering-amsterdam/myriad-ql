package sc.ql.gui.values;

public class StringValue extends Value {
	private final String value;
	
	public StringValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public Value equals(Value value) {
        return value.equals(this);
    }
	
	@Override
	public BooleanValue equals(StringValue value) {
        return new BooleanValue(this.value == value.getValue());
    }
	
	@Override
	public Value equalsNot(Value value) {
        return value.equalsNot(this);
    }
	
	@Override
	public BooleanValue equalsNot(StringValue value) {
        return new BooleanValue(this.value != value.getValue());
    }
	
}
