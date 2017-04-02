package qls.evaluation;

import QL.ast.type.Type;
import qls.ast.widget.Widget;

public class DefaultWidget {
	
	public Widget widget;
	public Type type;
	
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
