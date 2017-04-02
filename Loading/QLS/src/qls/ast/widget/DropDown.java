package qls.ast.widget;

import QL.ast.type.BooleanType;
import QL.ast.type.Type;
import QL.ui.field.Field;
import qls.evaluation.Evaluator;

public class DropDown extends Widget{
	
	private final String yes;
	private final String no;
	
	public DropDown(String yes, String no, int line) {
		super(line);
		this.yes = yes;
		this.no = no;
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
