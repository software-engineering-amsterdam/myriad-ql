package QL.ui;

public class Style {
	private final int width;
	private final String font;
	private final int fontSize;
	private final String color;
	
	public Style(int width, String font, int fontSize, String color) {
		this.width = width;
		this.font = font;
		this.fontSize = fontSize;
		this.color = color;
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
}
