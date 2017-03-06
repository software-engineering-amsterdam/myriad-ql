package value;

import ast.atom.Atom;
import ast.type.Type;

public abstract class Value {
		
	public abstract Atom getValue();
	public abstract Type getType();
}
