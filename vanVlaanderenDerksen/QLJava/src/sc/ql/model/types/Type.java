package sc.ql.model.types;

import sc.ql.model.Node;

public abstract class Type extends Node {
	
	public String toString() {
		return "undefined type";
	}
	
	public Boolean isNumericType() {
		return false;
	}
	
	public Boolean isBooleanType() {
		return false;
	}
	
	public Boolean isStringType() {
		return false;
	}
	
	public abstract Boolean isCompatibleWith(Type type);
	
	public Boolean isCompatibleWith(BooleanType type) {
		return false;
	}
	
	public Boolean isCompatibleWith(IntegerType type) {
		return false;
	}
	
	public Boolean isCompatibleWith(MoneyType type) {
		return false;
	}
	
	public Boolean isCompatibleWith(StringType type) {
		return false;
	}
	
}
