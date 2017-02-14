package ast.type;

public class StringType extends Type {

	public StringType() {
		super("string");
	}
	
	@Override
	public void accept(ast.Visitor v) {
		v.visit(this);		
	}
}
