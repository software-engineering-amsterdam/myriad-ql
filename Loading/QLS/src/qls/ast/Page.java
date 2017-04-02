package qls.ast;

import QL.ast.Node;

import java.util.List;

public class Page extends Node {
	
	private final String name;
	private final List<Section> sections;
	private final List<DefaultWidget> defaultWidgets;

	public Page(String name, List<Section> sections, List<DefaultWidget> defaultWidgets, int line) {
		super(line);
		this.name = name;
		this.sections = sections;
		this.defaultWidgets = defaultWidgets;
	}
	
	public void accept(StylesheetVisitor v) {
		v.visit(this);
	}
	
	
	public String getName() {
		return name;
	}

	public List<Section> getSections() {
		return sections;
	}

	public List<DefaultWidget> getDefaultWidgets() {
		return defaultWidgets;
	}

}
