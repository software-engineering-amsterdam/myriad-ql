package ast.atom;

import ast.ExpressionVisitor;
import ast.type.Type;

public class BoolAtom extends Atom {
    
	private Boolean value;

    public BoolAtom(Boolean value, int line) {
    	super(line);
    	this.value = value;
    }
    
    public BoolAtom(int line) {
    	super(line);
    	this.value = null;
    }
    
    @Override
    public boolean isSet() {
    	return value != null;
    }

	@Override
    public BoolAtom and(Atom other) {
		
    	if (isSet() || other.isSet()) {
    		return new BoolAtom(0);
    	}
		
    	return new BoolAtom(value && other.getValue(), getLine());
    }

	@Override
	public BoolAtom or(Atom other) {
		
    	if (isSet() || other.isSet()) {
    		return new BoolAtom(0);
    	}
		
		return new BoolAtom(value || other.getValue(), getLine());
	}

	@Override
	public BoolAtom eq(Atom other) {
		
    	if (isSet() || other.isSet()) {
    		return new BoolAtom(0);
    	}
		
		return new BoolAtom(value == other.getValue(), getLine());
	}

	@Override
	public BoolAtom notEq(Atom other) {
		
    	if (isSet() || other.isSet()) {
    		return new BoolAtom(0);
    	}
		
		return new BoolAtom(value != other.getValue(), getLine());
	}

	@Override
	public BoolAtom not() {
		
    	if (isSet()) {
    		return new BoolAtom(0);
    	}
		
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