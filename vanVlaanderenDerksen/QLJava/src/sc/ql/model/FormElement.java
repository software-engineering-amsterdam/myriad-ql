package sc.ql.model;

public interface FormElement extends Node {	
	public enum Type {
		QUESTION, IF_STATEMENT;
	}
	
	public Type getElementType();
}