package qls.ast;

import java.util.List;

import QL.ast.Node;
import qls.ast.Page;

public class Stylesheet extends Node {
	
	private final String name;
	private final List<Page> page;

	public Stylesheet(String name, List<Page> page, int line) {
		super(line);
		this.name = name;
		this.page = page;
	}

}
