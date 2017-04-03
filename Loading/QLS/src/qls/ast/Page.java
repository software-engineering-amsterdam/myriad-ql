package qls.ast;

import QL.ast.Node;

import java.util.Iterator;
import java.util.List;

public class Page extends Node implements Iterable<Section> {
	
	private final List<Section> sections;
	private final List<DefaultWidget> defaultWidgets;

	public Page(List<Section> sections, List<DefaultWidget> defaultWidgets, int line) {
		super(line);
		this.sections = sections;
		this.defaultWidgets = defaultWidgets;
	}
	
	public void accept(StylesheetVisitor v) {
		v.visit(this);
	}

	public List<DefaultWidget> getDefaultWidgets() {
		return defaultWidgets;
	}

	@Override
	public Iterator<Section> iterator() {
		return sections.iterator();
	}

}
