package ast.type;

public class DecimalType extends Type {

	public DecimalType() {
		super("decimal");
	}
		
	@Override
	public void accept(ast.Visitor v) {
		v.visit(this);		
	}
	
}
