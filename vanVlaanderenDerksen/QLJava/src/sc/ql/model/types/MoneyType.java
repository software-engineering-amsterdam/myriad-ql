package sc.ql.model.types;

public class MoneyType extends Type {
	
	@Override
	public String toString() {
		return "Money";
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
	public Boolean isCompatibleWith(MoneyType type) {
		return true;
	}
	
}
