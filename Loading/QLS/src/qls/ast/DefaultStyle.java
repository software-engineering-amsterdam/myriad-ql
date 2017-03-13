package qls.ast;

import QL.ast.type.Type;
import qls.ast.widget.Widget;

public class DefaultStyle extends DefaultWidget {
	
	private final int width;
	private final String font;
	private final int fontSize;
	private final String color;
	
	public DefaultStyle(Type type, int width, String font, int fontSize, String color, Widget widget, int line) {
		super(type, widget, line);
		this.width = width;
		this.font = font;
		this.fontSize = fontSize;
		this.color = color;
	}
	
}
