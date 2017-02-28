package value;

import ast.atom.Atom;
import ast.type.Type;

public class EmptyValue extends Value {
	
	@Override
	public Atom getValue() { // TODO is this still necessary
		return null;
	}

}
