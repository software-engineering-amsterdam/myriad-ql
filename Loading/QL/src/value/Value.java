package value;

import ast.atom.Atom;
import ast.atom.BoolAtom;
import ast.atom.IntegerAtom;
import ast.atom.StringAtom;

public class Value {
		
	private Atom atom;
	
	// TODO change!
	public Value() {
		this.atom = null;
	}
	
	public Value(String value) {
		this.atom = new StringAtom(value, 0);
	}
	
	public Value(Integer value) {
		this.atom = new IntegerAtom(value, 0);
	}
	
	public Value(Boolean value) {
		this.atom = new BoolAtom(value, 0);
	}
	
	public Atom getValue() {
		return atom;
	}

}
