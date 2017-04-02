package QL.ui;

import QL.ast.type.Type;
import QL.ui.field.Field;

public class Style {
	private final int width;
	private final String font;
	private final int fontSize;
	private final String color;
	private final Type type;
	
	public Style(int width, String font, int fontSize, String color, Type type) {
		this.width = width;
		this.font = font;
		this.fontSize = fontSize;
		this.color = color;
		this.type = type;
	}
	
	public int getWidth() {
		return width;
	}
	
	public String getFont() {
		return font;
	}
	
	public int getFontSize() {
		return fontSize;
	}
	
	public String getColor() {
		return color;
	}
	
	public Type getType() {
		return type;
	}
}
