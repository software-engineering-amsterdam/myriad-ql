package qls.ast;

import java.util.List;

public class PageWithDefault extends Page {
	
	private final List<DefaultWidget> defaultWidgets;

	public PageWithDefault(String name, List<Section> sections, List<DefaultWidget> defaultWidgets, int line) {
		super(name, sections, line);
		this.defaultWidgets = defaultWidgets;
		
	}
	
	public List<DefaultWidget> defaultWidgets() {
		return defaultWidgets;
	}
	
//	public void accept(StylesheetVisitor v) {
//		v.visit(this);
//	}
}
