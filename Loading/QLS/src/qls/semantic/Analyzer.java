package qls.semantic;

import java.util.List;

import QL.ReferenceTable;
import QL.message.Message;
import qls.ast.Stylesheet;

public class Analyzer {
	
	private final Environment environment;
	
	public Analyzer(ReferenceTable referenceTable, List<Message> messages) {
		this.environment = new Environment(referenceTable, messages);
	}
	
	public void analyze(Stylesheet stylesheet) {
		
		VerifyQuestions verifyQuestions = new VerifyQuestions(environment);
		verifyQuestions.visit(stylesheet);
		
		environment.checkCoverage();

		VerifyTypes verifyTypes = new VerifyTypes(environment);
		verifyTypes.visit(stylesheet);
    }
	
}
