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
		stylesheet.accept(verifyQuestions);
		
		environment.checkCoverage();

		VerifyTypes verifyTypes = new VerifyTypes(environment);
		stylesheet.accept(verifyTypes);
    }
	
}
