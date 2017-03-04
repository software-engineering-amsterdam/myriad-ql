package value;

import ast.atom.Atom;
import ast.type.Type;
import ui.QControl;

public abstract class Value {
		
	public abstract Atom getValue();
	// public abstract Atom getValue();
	public abstract Type getType();
	public abstract QControl getField();
}
