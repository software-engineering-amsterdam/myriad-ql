package qls.ast;

import java.util.List;

public class SectionWithDefault extends Section {
	
	private final DefaultStyle defaultStyle;
	
	public SectionWithDefault(String name, List<Question> questions, DefaultStyle defaultStyle, int line) {
		super(name, questions, line);
		this.defaultStyle = defaultStyle;
	}

}
