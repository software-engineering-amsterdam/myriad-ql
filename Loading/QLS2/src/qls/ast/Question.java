package qls.ast;

import QL.ast.Node;
import qls.ast.widget.Widget;

public class Question extends Node {
	
	final private String name;
	final private Widget widget;
	
	public Question(String name, Widget widget, int line) {
		super(line);
		this.name = name;
		this.widget = widget;
	}

}
