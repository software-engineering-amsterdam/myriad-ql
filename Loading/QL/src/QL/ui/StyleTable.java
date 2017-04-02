package QL.ui;

import QL.ReferenceTable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;
import java.util.Map;

public class StyleTable {
	
	private Map<String, Style> styles;
	
// TODO static default style?
// TODO Remove type from style?
//	private static Style = new Style(350, "Arial", 12, "#0000FF", 
//			variables.getType(variable)
	
	StyleTable() {
		styles = new HashMap<>();
	}
	
	public void addDefaults(ReferenceTable variables) {
		for (String variable : variables) {
			if (!isStyled(variable)) {
				styles.put(variable, new Style(350, "Arial", 12, "#0000FF", 
						variables.getType(variable)));
			}
		}
	}
	
	// TODO inversion of control??
	void add(String name, Style style) {
		styles.put(name, style);
	}
	
	void applyStyle(String name, Label label) {
		if (!styles.containsKey(name)) {
			throw new RuntimeException("The style of variable " + name + " is undefined.");
		}
		setSettings(label, styles.get(name));
	}
	
	public boolean isStyled(String variable) {
		return styles.containsKey(variable);
	}
	
	// TODO move to different class?
    private void setSettings(Label label, Style style) {
		label.setFont(new Font(style.getFont(), style.getFontSize()));
		label.setTextFill(Color.web(style.getColor()));
    	label.setPrefWidth(style.getWidth());
    }
}
