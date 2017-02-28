package ast.type;

public class IntegerType extends Type {

    // TODO why can you not use the constructor of superclass Type directly
	public IntegerType(int line) {
		super("integer", line);
	}
	
	@Override
	public void accept(ast.Visitor v) {
		// TODO empty accept
	}
	
}
