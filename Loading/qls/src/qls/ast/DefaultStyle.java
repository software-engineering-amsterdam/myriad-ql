package qls.ast;

import ql.ui.Style;
import ql.ast.type.Type;
import qls.ast.widget.Widget;

public class DefaultStyle extends DefaultWidget {
	
	private final Style style;
	
	public DefaultStyle(Type type, Style style, Widget widget, int line) {
		super(type, widget, line);
		this.style = style;
	}
	
	public Style getStyle() {
		return style;
	}
	
	public void accept(DefaultVisitor v) {
		v.visit(this);	
	}
	
}
