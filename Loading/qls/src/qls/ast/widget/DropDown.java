package qls.ast.widget;

import ql.ast.type.BooleanType;
import ql.ast.type.Type;
import ql.ui.field.Field;
import qls.evaluation.Evaluator;

public class DropDown extends Widget{
	
	private final String trueText;
	private final String falseText;
	
	public DropDown(String yes, String no, int line) {
		super(line);
		this.trueText = yes;
		this.falseText = no;
	}

	@Override
	public Type getType() {
		return new BooleanType();
	}
	
	public String getTrueText() {
		return trueText;
	}
	
	public String getFalseText() {
		return falseText;
	}
	
	@Override
	public Field accept(Evaluator v) {
		return v.visit(this);	
	}
}
