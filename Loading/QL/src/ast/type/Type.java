package ast.type;

public abstract class Type {
	
	final private String type;
	
	public Type(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
