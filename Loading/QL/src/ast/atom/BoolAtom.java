package ast.atom;

import ast.ExpressionVisitor;
import ast.type.Type;

public class BoolAtom extends Atom {
    
	private boolean value;

    public BoolAtom(Boolean value, int line) {
    	super(line);
    	this.value = value;
    }

	@Override
    public BoolAtom and(Atom other) {
    	return new BoolAtom(value && other.getValue(), getLine());
    }

	@Override
	public BoolAtom or(Atom other) {
		return new BoolAtom(value || other.getValue(), getLine());
	}

	@Override
	public BoolAtom eq(Atom other) {
		return new BoolAtom(value == other.getValue(), getLine());
	}

	@Override
	public BoolAtom notEq(Atom other) {
		return new BoolAtom(value != other.getValue(), getLine());
	}

	@Override
	public BoolAtom not() {
		return new BoolAtom(!value, getLine());
	}

	@Override
    public Boolean getValue() {
        return this.value;
    }

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}