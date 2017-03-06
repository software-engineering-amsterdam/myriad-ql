package ast.atom;

import ast.ExpressionVisitor;
import ast.type.Type;

public class IntegerAtom extends Atom {
		
	private final Integer number;
	
    public IntegerAtom(Integer number, int line) {
    	super(line);
        this.number = number;
    }
    
    public IntegerAtom(int line) {
    	super(line);
    	this.number = null;
    }
    
    @Override
    public boolean isSet() {
    	return number != null;
    }
    
	@Override
	public Atom add(Atom other) {
		
		if (!isSet() || !other.isSet()) {
			return new IntegerAtom(0);
		}
		
		return new IntegerAtom(number + other.getNumber(), getLine());
	}
	
	@Override
	public Atom sub(Atom other) {
		
		if (!isSet() || !other.isSet()) {
			return new IntegerAtom(0);
		}
		
		return new IntegerAtom(number - other.getNumber(), getLine());
	}
	
	@Override
	public Atom mul(Atom other) {
		
		if (!isSet() || !other.isSet()) {
			return new IntegerAtom(0);
		}
		
		return new IntegerAtom(number * other.getNumber(), getLine());
	}

	@Override
	public Atom div(Atom other) {
		
		// TODO division by null
		if (!isSet() || !other.isSet()) {
			return new IntegerAtom(0);
		}
		
		return new IntegerAtom(number / other.getNumber(), getLine());
	}

	@Override
	public BoolAtom eq(Atom other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolAtom(0);
		}
		
		return new BoolAtom(number.equals(other.getNumber()), getLine());
	}

	@Override
	public BoolAtom notEq(Atom other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolAtom(0);
		}
		
		return new BoolAtom(!number.equals(other.getNumber()), getLine());
	}
	
	@Override
	public BoolAtom greaterEq(Atom other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolAtom(0);
		}
		
		return new BoolAtom(number >= other.getNumber(), getLine());
	}

	@Override
	public BoolAtom greater(Atom other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolAtom(0);
		}
		
		return new BoolAtom(number > other.getNumber(), getLine());
	}

	@Override
	public BoolAtom lessEq(Atom other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolAtom(0);
		}
		
		return new BoolAtom(number <= other.getNumber(), getLine());
	}

	@Override
	public BoolAtom less(Atom other) {
		
		if (!isSet() || !other.isSet()) {
			return new BoolAtom(0);
		}
		
		return new BoolAtom(number < other.getNumber(), getLine());
	}
	
	@Override
	public Atom plus() {
		
		if (!isSet()) {
			return new IntegerAtom(0);
		}
		
		return new IntegerAtom(+ number, getLine());
	}

	@Override
	public Atom min() {
		
		if (!isSet()) {
			return new IntegerAtom(0);
		}
		
		return new IntegerAtom(- number, getLine());
	}

	@Override
    public Integer getNumber() {
        return this.number;
    }
	

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}