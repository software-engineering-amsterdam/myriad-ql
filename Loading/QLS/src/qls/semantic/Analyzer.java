package qls.semantic;

import java.util.Map;

import QL.Faults;
import QL.ReferenceTable;
import QL.ast.type.Type;
import QL.semantic.Environment;
import qls.ast.Stylesheet;

public class Analyzer {
	
	private final ReferenceTable variables;
	
	public Analyzer(ReferenceTable variables) {
		this.variables = variables;
	}
	
	public Faults analyze(Stylesheet stylesheet) {
		
		VerifyQuestions verifyQuestions = new VerifyQuestions(variables);
		verifyQuestions.visit(stylesheet);
		
		return environment.getFaults();
	}

}
