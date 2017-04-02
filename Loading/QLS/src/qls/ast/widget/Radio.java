package qls.ast.widget;

import QL.ast.type.BooleanType;
import QL.ast.type.Type;
import QL.ui.field.Field;
import qls.evaluation.Evaluator;

public class Radio extends Widget {
	
	private final String checked;
	private final String unchecked;
	
	public Radio(String checked, String unchecked, int line) {
		super(line);
		this.checked = checked;
		this.unchecked = unchecked;
	}

	@Override
	public Type getType() {
		return new BooleanType(1);
	}
	
	@Override
	public Field accept(Evaluator v) {
		return v.visit(this);	
	}
}
