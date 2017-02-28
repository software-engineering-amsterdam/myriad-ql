package ast.type;

public class DecimalType extends Type {

	public DecimalType(int line) {
		super("decimal", line);
	}
		
	@Override
	public void accept(ast.Visitor v) {
		v.visit(this);		
	}
	
}
