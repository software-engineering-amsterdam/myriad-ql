package qls.ast;

import java.util.List;

import QL.ast.Node;

public class Page extends Node {
	
	private final String name;
	private final List<Section> sections;

	public Page(String name, List<Section> sections, int line) {
		super(line);
		this.name = name;
		this.sections = sections;
	}
	
	public void accept(StylesheetVisitor v) {
		v.visit(this);
	}

	public List<Section> getSections() {
		return sections;
	}

}
