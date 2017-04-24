package qls.ast.widget;

import ql.ast.type.StringType;
import ql.ast.type.Type;
import ql.ui.field.Field;
import qls.evaluation.Evaluator;

public class TextField extends Widget {
	
	public TextField(int line) {
		super(line);
	}

	@Override
	public Type getType() {
		return new StringType();
	}
	
	@Override
	public Field accept(Evaluator v) {
		return v.visit(this);	
	}
}
