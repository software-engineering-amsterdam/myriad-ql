package sc.ql.model;

public class Atom<T> implements Node {
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