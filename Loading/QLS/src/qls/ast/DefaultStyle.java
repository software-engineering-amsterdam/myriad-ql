package qls.ast;

import QL.ast.Node;
import qls.ast.widget.Widget;

public class DefaultStyle extends Node {
	
	private final int width;
	private final String font;
	private final String fontSize;
	private final String color;
	private final Widget widget;
	
	public DefaultStyle(int width, String font, String fontSize, String color, Widget widget, int line) {
		super(line);
		this.width = width;
		this.font = font;
		this.fontSize = fontSize;
		this.color = color;
		this.widget = widget;
	}
	
}
