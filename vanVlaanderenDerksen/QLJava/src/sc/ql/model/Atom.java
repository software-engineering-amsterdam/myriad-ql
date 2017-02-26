package sc.ql.model;

import sc.ql.model.expressions.Expression;

public class Atom<T> extends Expression {
	private final Type type;
	private final T value;
	
	public enum Type {
		ID, BOOLEAN, DATE, FLOAT, INTEGER, MONEY, STRING;
	}
	
	public Atom(Type type, T value) {
		this.type = type;
		this.value = value;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public T getValue() {
		return this.value;
	}
}