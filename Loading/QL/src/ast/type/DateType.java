package ast.type;

public class DateType extends Type {
	
	public DateType() {
		super("date");
	}
	
	@Override
	public void accept(ast.Visitor v) {
		v.visit(this);		
	}

}
