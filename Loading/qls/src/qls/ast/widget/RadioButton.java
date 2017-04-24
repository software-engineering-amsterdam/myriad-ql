package qls.ast.widget;

import ql.ast.type.BooleanType;
import ql.ast.type.Type;
import ql.ui.field.Field;
import qls.evaluation.Evaluator;

public class RadioButton extends Widget {
	
	private final String trueText;
	private final String falseText;
	
	public RadioButton(String trueText, String falseText, int line) {
		super(line);
		this.trueText = trueText;
		this.falseText = falseText;
	}
	
	public String getTrueText() {
		return trueText;
	}
	
	public String getFalseText() {
		return falseText;
	}
	

	@Override
	public Type getType() {
		return new BooleanType();
	}
	
	@Override
	public Field accept(Evaluator v) {
		return v.visit(this);	
	}
}
