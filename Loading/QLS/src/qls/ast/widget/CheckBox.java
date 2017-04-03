 package qls.ast.widget;

 import QL.ast.type.BooleanType;
 import QL.ast.type.Type;
import QL.ui.field.Field;
import qls.evaluation.Evaluator;

 public class CheckBox extends Widget {

	public CheckBox(int line) {
		super(line);
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
