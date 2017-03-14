package qls.semantic;

import java.util.Map;

import QL.Faults;
import QL.ast.type.Type;
import qls.ast.Stylesheet;

public class Analyzer {
	
	private Environment environment;
	
	public Analyzer(Map<String, Type> variableTypes) {
		this.environment = new Environment(variableTypes);
	}
	
	public Faults analyze(Stylesheet stylesheet) {
		VerifyQuestions verifyQuestions = new VerifyQuestions(environment);
		verifyQuestions.visit(stylesheet);
		
		// TODO implement
		return new Faults();
	}

}
