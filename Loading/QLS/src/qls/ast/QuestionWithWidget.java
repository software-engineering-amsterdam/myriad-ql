package qls.ast;

import qls.ast.widget.Widget;

public class QuestionWithWidget extends Question {

	final private Widget widget;
	
	public QuestionWithWidget(String name, Widget widget, int line) {
		super(name, line);
		this.widget = widget;
	}
	
	public void accept(StylesheetVisitor v) {
		v.visit(this);
	}

}
