package sc.ql.model.types;

public class BooleanType extends Type {

	@Override
	public String toString() {
		return "Boolean";
	}
	
	@Override
	public Boolean isBooleanType() {
		return true;
	}

	@Override
	public Boolean isCompatibleWith(Type type) {
		return type.isCompatibleWith(this);
	}
	
	@Override
	public Boolean isCompatibleWith(BooleanType type) {
		return true;
	}
	
}
