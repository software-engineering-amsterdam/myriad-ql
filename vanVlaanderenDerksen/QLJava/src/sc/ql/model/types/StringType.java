package sc.ql.model.types;

public class StringType extends Type {

	@Override
	public String toString() {
		return "String";
	}
	
	@Override
	public Boolean isStringType() {
		return true;
	}
	
	@Override
	public Boolean isCompatibleWith(Type type) {
		return type.isCompatibleWith(this);
	}
	
	@Override
	public Boolean isCompatibleWith(StringType type) {
		return true;
	}
	
}
