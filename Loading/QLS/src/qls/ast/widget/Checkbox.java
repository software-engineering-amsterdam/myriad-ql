 package qls.ast.widget;

 import QL.ast.type.BooleanType;
 import QL.ast.type.Type;
 import qls.evaluation.Evaluator;

 public class Checkbox extends Widget {

	public Checkbox(int line) {
		super(line);
	}

	@Override
	public Type getType() {
		return new BooleanType(1);
	}

	public void accept(Evaluator evaluator) {
		return;
		
	}
}
