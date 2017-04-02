package qls.ast.widget;

import QL.ast.type.StringType;
import QL.ast.type.Type;
import QL.ui.field.Field;
import qls.evaluation.Evaluator;

public class TextField extends Widget {
	
	public TextField(int line) {
		super(line);
	}

	@Override
	public Type getType() {
		return new StringType(1);
	}
	
	@Override
	public Field accept(Evaluator v) {
		return v.visit(this);	
	}
}
