 package qls.ast.widget;

 import ql.ast.type.BooleanType;
 import ql.ast.type.Type;
import ql.ui.field.Field;
import qls.evaluation.Evaluator;

 public class CheckBox extends Widget {

	public CheckBox(int line) {
		super(line);
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
