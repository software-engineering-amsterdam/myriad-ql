package QL.ui;

import java.util.HashMap;
import java.util.Map;

import QL.ReferenceTable;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class StyleTable {
	
	private Map<String, Style> styles;
	
	public StyleTable(ReferenceTable variables) {
		
		Style defaultStyle = new Style(150, "Arial", 12, "Color.RED");
			
		styles = new HashMap<>();
		for (String variable : variables) {
			styles.put(variable, defaultStyle);
		}
	}
	
	public StyleTable(Map<String, Style> styles) {
		this.styles = styles;
	}
	
	public void applyStyle(String name, Label label) {
		if (!styles.containsKey(name)) {
			throw new RuntimeException("The style of variable " + name + " is undefined.");
		}
		setSettings(label, styles.get(name));
	}
	
	// TODO move to different class?
    private void setSettings(Label label, Style style) {
    	label.setFont(new Font(style.getFont(), style.getFontSize()));
    	// label.setTextFill(style.getColor()); // TODO convert to java fx color
    	label.setPrefWidth(style.getWidth());
    }
}
