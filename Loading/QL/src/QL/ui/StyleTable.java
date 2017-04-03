package QL.ui;

import QL.ReferenceTable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;

public class StyleTable {
	
	private Map<String, Style> styles;
	
	StyleTable() {
		styles = new HashMap<>();
	}
	
	void addDefaults(ReferenceTable variables) {		
		Style defaultStyle = new Style(350, "Arial", 12, "#0000FF");
		for (String variable : variables) {
			if (!isStyled(variable)) {
				styles.put(variable, defaultStyle);
			}
		}
	}
	
	void add(String name, Style style) {
		styles.put(name, style);
	}
	
	void applyStyle(String name, Label label) {
		if (!styles.containsKey(name)) {
			throw new RuntimeException("The style of variable " + name + " is undefined.");
		}
		style(label, styles.get(name));
	}
	
	public boolean isStyled(String variable) {
		return styles.containsKey(variable);
	}
	
    private void style(Label label, Style style) {
		label.setFont(new Font(style.getFont(), style.getFontSize()));
		label.setTextFill(Color.web(style.getColor()));
    	label.setPrefWidth(style.getWidth());
    }
}
