package qls.ast.widget;

import QL.ast.type.BooleanType;
import QL.ast.type.Type;
import QL.ui.field.Field;
import qls.evaluation.Evaluator;

public class Radio extends Widget {
	
	private final String trueText;
	private final String falseText;
	
	public Radio(String trueText, String falseText, int line) {
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
		return new BooleanType(1);
	}
	
	@Override
	public Field accept(Evaluator v) {
		return v.visit(this);	
	}
}
