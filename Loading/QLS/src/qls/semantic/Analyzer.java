package qls.semantic;

import java.util.Map;

import QL.ReferenceTable;
import QL.ast.type.Type;
import qls.ast.Stylesheet;

public class Analyzer {
	
	private final Environment environment;
	
	public Analyzer(Map<String, Type> referenceTable) {
		this.environment = new Environment(referenceTable);
	}
	
	public void analyze(Stylesheet stylesheet) {
		
		VerifyQuestions verifyQuestions = new VerifyQuestions(environment);
		verifyQuestions.visit(stylesheet);
		
		// return environment.getFaults();
	}
	
	

}
