package qls.ast.widget;

import QL.ast.type.IntegerType;
import QL.ast.type.Type;

public class Spinbox extends Widget {

	public Spinbox(int line) {
		super(line);
	}

	@Override
	public Type getType() {
		return new IntegerType(1);
	}

}
