package qls.ast;

import QL.ast.Node;
import QL.ast.type.Type;
import qls.ast.widget.Widget;

public class DefaultWidget extends Node {
	
	private final Type type;
	private final Widget widget;
	
	public DefaultWidget(Type type, Widget widget, int line) {
		super(line);
		this.type = type;
		this.widget = widget;
	}

	public void accept(DefaultVisitor v) {
		v.visit(this);	
	}

	public Type getType() {
		return type;
	}

	public Widget getWidget() {
		return widget;
	}
}
