package qls.ast.widget;

import QL.ast.Node;
import QL.ast.type.Type;

public abstract class Widget extends Node {

	Widget(int line) {
		super(line);	
	}

	public abstract Type getType();

}
