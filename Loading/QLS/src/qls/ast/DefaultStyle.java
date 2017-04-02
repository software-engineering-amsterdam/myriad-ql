package qls.ast;

import QL.ui.Style;
import qls.ast.widget.Widget;

public class DefaultStyle extends DefaultWidget {
	
	private final Style style;
	
	public DefaultStyle(Style style, Widget widget, int line) {
		super(style.getType(), widget, line);
		this.style = style;
	}
	
	public Style getStyle() {
		return style;
	}
	
	public void accept(DefaultVisitor v) {
		v.visit(this);	
	}
	
}
