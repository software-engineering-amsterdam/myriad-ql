package ast.type;

public class StringType extends Type {

	public StringType(int line) {
		super("string", line);
	}
	
	@Override
	public void accept(ast.Visitor v) {
		// TODO empty accept
	}
}
