package qls.ast.widget;

import QL.ast.Node;
import QL.ast.type.Type;
import QL.ui.field.Field;
import qls.evaluation.Evaluator;

public abstract class Widget extends Node {

	Widget(int line) {
		super(line);	
	}

	public abstract Type getType();
	
	public abstract Field accept(Evaluator v); 

}
