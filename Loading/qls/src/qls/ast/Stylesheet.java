package qls.ast;

import ql.ast.Node;

import java.util.Iterator;
import java.util.List;

public class Stylesheet extends Node implements Iterable<Page> {

    private final List<Page> pages;

	public Stylesheet(List<Page> pages, int line) {
		super(line);
		this.pages = pages;
	}
	
	public void accept(StylesheetVisitor v) {
		v.visit(this);
	}

	@Override
	public Iterator<Page> iterator() {
		return pages.iterator();
	}

	

}
