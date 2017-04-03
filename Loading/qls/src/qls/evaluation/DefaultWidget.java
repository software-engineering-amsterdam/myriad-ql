package qls.evaluation;

import ql.ast.type.Type;
import qls.ast.widget.Widget;

class DefaultWidget {
	
	private final Widget widget;
	private final Type type;
	
	DefaultWidget(Widget widget, Type type) {
		this.widget = widget;
		this.type = type;
	}
	
	public Widget getWidget() {
		return widget;
	}
	
	public Type getType() {
		return type;
	}
}
