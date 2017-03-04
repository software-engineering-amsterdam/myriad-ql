package ast.atom;

import ast.ExpressionVisitor;
import ast.type.Type;

public class StringAtom extends Atom {
    private String str;

    public StringAtom(String str, int line) {
    	super(line);
        this.str = str;
    }

    @Override
	public BoolAtom eq(Atom other) {
		return new BoolAtom(str.equals(other.getString()), getLine());
	}

	@Override
	public BoolAtom notEq(Atom other) {
		return new BoolAtom(!str.equals(other.getString()), getLine());
	}
		
	@Override
	public String getString() {
		return str;
	}
	
	@Override
	public String getType() {
		return "string";
	}

//	@Override
//	public Atom evaluate(Environment env) {
//		return this;
//	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}

}