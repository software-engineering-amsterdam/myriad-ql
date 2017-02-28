package ast.atom;

import ast.ExpressionVisitor;
import ast.type.Type;

public class IntegerAtom extends Atom {
		
	private final Integer number;
	
    public IntegerAtom(Integer number, int line) {
    	super(line);
        this.number = number;
    }

	@Override
	public Atom add(Atom other) {
		
		if (other.getNumber() == null) {
			return null;
		}
		
		return new IntegerAtom(number + other.getNumber(), getLine());
	}
	
	@Override
	public Atom sub(Atom other) {
		
		if (other.getNumber() == null) {
			return null;
		}
		
		return new IntegerAtom(number - other.getNumber(), getLine());
	}
	
	@Override
	public Atom mul(Atom other) {
		
		if (other.getNumber() == null) {
			return null;
		}
		
		return new IntegerAtom(number * other.getNumber(), getLine());
	}

	@Override
	public Atom div(Atom other) {
		if (other.getNumber() == 0 || other.getNumber() == null) {
			return null;
		}
		
		return new IntegerAtom(number / other.getNumber(), getLine());
	}

	@Override
	public BoolAtom eq(Atom other) {
		return new BoolAtom(number.equals(other.getNumber()), getLine());
	}

	@Override
	public BoolAtom notEq(Atom other) {
		return new BoolAtom(!number.equals(other.getNumber()), getLine());
	}
	
	@Override
	public BoolAtom greaterEq(Atom other) {
		return new BoolAtom(number >= other.getNumber(), getLine());
	}

	@Override
	public BoolAtom greater(Atom other) {
		return new BoolAtom(number > other.getNumber(), getLine());
	}

	@Override
	public BoolAtom lessEq(Atom other) {
		return new BoolAtom(number <= other.getNumber(), getLine());
	}

	@Override
	public BoolAtom less(Atom other) {
		return new BoolAtom(number < other.getNumber(), getLine());
	}
	
	@Override
	public Atom plus() {
		return new IntegerAtom(+ number, getLine());
	}

	@Override
	public Atom min() {
		return new IntegerAtom(- number, getLine());
	}

	@Override
    public Integer getNumber() {
        return this.number;
    }
	
    @Override
    public String getType() {
    	return "integer";
    }

//	@Override
//	public Atom evaluate(semantic.Environment env) {
//		return this;
//	}

	@Override
	public <T> T accept(ExpressionVisitor<T> v) {
		return v.visit(this);
	}
}