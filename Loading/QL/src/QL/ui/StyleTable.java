package QL.ui;

import QL.ReferenceTable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;

class StyleTable {
	
	private final Map<String, Style> styles;

	StyleTable() {
		styles = new HashMap<>();
	}
	
	void addDefaults(ReferenceTable names) {
		Style defaultStyle = new Style(350, "Arial", 12, "#0000FF");
		for (String name : names) {
			if (!isStyled(name)) {
				styles.put(name, defaultStyle);
			}
		}
	}

	void add(String name, Style style) {
		styles.put(name, style);
	}
	
	void applyStyle(String name, Label label) {
		if (!styles.containsKey(name)) {
				throw new RuntimeException("The style of question " + name + " is undefined.");
		}
		style(label, styles.get(name));
	}
	
	boolean isStyled(String question) {
		return styles.containsKey(question);
	}
	
    private void style(Label label, Style style) {
		label.setFont(new Font(style.getFont(), style.getFontSize()));
		label.setTextFill(Color.web(style.getColor()));
    	label.setPrefWidth(style.getWidth());
    }
}
