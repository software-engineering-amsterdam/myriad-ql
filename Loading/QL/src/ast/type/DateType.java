package ast.type;

public class DateType extends Type {
	
	public DateType(int line) {
		super("date", line);
	}
	
	@Override
	public void accept(ast.Visitor v) {
		v.visit(this);		
	}

}
