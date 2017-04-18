package qls.ast.widget;

import ql.ast.Node;
import ql.ast.type.Type;
import ql.ui.field.Field;
import qls.evaluation.Evaluator;

public abstract class Widget extends Node {

	Widget(int line) {
		super(line);	
	}

	public abstract Type getType();
	
	public abstract Field accept(Evaluator v); 

}
