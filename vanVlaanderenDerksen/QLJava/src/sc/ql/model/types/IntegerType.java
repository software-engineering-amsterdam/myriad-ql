package sc.ql.model.types;

public class IntegerType extends Type {

	@Override
	public String toString() {
		return "Integer";
	}
	
	@Override
	public Boolean isNumericType() {
		return true;
	}
	
	@Override
	public Boolean isCompatibleWith(Type type) {
		return type.isCompatibleWith(this);
	}
	
	@Override
	public Boolean isCompatibleWith(IntegerType type) {
		return true;
	}
	
}
