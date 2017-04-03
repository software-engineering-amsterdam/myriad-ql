package qls.ast;

import QL.ast.Node;

import java.util.List;

public class Stylesheet extends Node {

    private final List<Page> pages;

	public Stylesheet(String name, List<Page> pages, int line) {
		super(line);
        String name1 = name;
		this.pages = pages;
	}
	
	public void accept(StylesheetVisitor v) {
		v.visit(this);
	}

	public List<Page> getPages() {
		return pages;
	}

}
