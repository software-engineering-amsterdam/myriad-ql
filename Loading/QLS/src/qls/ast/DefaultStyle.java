package qls.ast;

import QL.ast.Node;
import qls.ast.widget.Widget;

public class DefaultStyle extends Node {
	
	private final String name;
	private final int width;
	private final String font;
	private final int fontSize;
	private final String color;
	private final Widget widget;
	
	public DefaultStyle(String name, int width, String font, int fontSize, String color, Widget widget, int line) {
		super(line);
		this.name = name;
		this.width = width;
		this.font = font;
		this.fontSize = fontSize;
		this.color = color;
		this.widget = widget;
	}
	
}
