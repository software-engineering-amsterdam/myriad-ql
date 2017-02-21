package sc.ql.model;

public class ID implements Node {
	private final String id;
	
	public ID(String id) {
		this.id = id;
	}
	
	public String toString() {
		return this.id;
	}
}
