package qls.ast;

import QL.ast.Node;
import QL.ast.type.Type;
import qls.ast.widget.Widget;

public class DefaultWidget extends Node {
	
	private Type type;
	private Widget widget;
	
	public DefaultWidget(Type type, Widget widget, int line) {
		super(line);
		this.type = type;
		this.widget = widget;
	}

}
