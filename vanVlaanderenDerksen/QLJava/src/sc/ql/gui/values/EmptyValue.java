package sc.ql.gui.values;

public class EmptyValue extends Value {

	@Override
	public Boolean isEmptyValue() {
		return true;
	}
	
	@Override
	public String toString() {
		return "Empty value";
	}
	
	@Override
	public Boolean getValue() {
		return false;
	}
	
}
