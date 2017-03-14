package qls.ast;

import java.util.List;

public class SectionWithDefault extends Section {
	
	private final List<DefaultWidget> defaultWidget;
	
	public SectionWithDefault(String name, List<Question> questions, List<DefaultWidget> defaultWidget, int line) {
		super(name, questions, line);
		this.defaultWidget = defaultWidget;
	}
	
//	public void accept(StylesheetVisitor v) {
//		v.visit(this);
//	}

}
