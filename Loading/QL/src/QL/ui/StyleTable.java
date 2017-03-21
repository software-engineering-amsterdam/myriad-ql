package QL.ui;

import QL.ReferenceTable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;

public class StyleTable {
	
	private Map<String, Style> styles;
	
	StyleTable(ReferenceTable variables) {
		
		Style defaultStyle = new Style(350, "Arial", 12, "#0000FF");
			
		styles = new HashMap<>();
		for (String variable : variables) {
			styles.put(variable, defaultStyle);
		}
	}
	
	public StyleTable(Map<String, Style> styles) {
		this.styles = styles;
	}
	
	void applyStyle(String name, Label label) {
		if (!styles.containsKey(name)) {
			throw new RuntimeException("The style of variable " + name + " is undefined.");
		}
		setSettings(label, styles.get(name));
	}
	
	// TODO move to different class?
    private void setSettings(Label label, Style style) {
    	label.setFont(new Font(style.getFont(), style.getFontSize()));
    	 label.setTextFill(Color.web(style.getColor()));
    	label.setPrefWidth(style.getWidth());
    }
}
