package qls.ast.widget;

import ql.ast.type.IntegerType;
import ql.ast.type.Type;
import ql.ui.field.Field;
import qls.evaluation.Evaluator;

public class NumberField extends Widget {
	
	public NumberField(int line) {
		super(line);
	}

	@Override
	public Type getType() {
		return new IntegerType();
	}
	
	@Override
	public Field accept(Evaluator v) {
		return v.visit(this);	
	}
}
