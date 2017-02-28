package ast.type;

public class MoneyType extends Type {
	
    // TODO why can you not use the constructor of superclass Type directly
	public MoneyType(int line) {
		super("money", line);
	}
	
	@Override
	public void accept(ast.Visitor v) {
		v.visit(this);		
	}
	
}
