package qls.semantic;

import java.util.List;
import java.util.Map;

import QL.ReferenceTable;
import QL.ast.type.Type;
import QL.message.Message;
import qls.ast.Stylesheet;

public class Analyzer {
	
	private final Environment environment;
	
	public Analyzer(ReferenceTable referenceTable) {
		this.environment = new Environment(referenceTable);
	}
	
	public void analyze(Stylesheet stylesheet) {
		
		VerifyQuestions verifyQuestions = new VerifyQuestions(environment);
		verifyQuestions.visit(stylesheet);
		
	}
	
	public List<Message> getMessages() {
		return environment.getMessages();
	}
	
	

}
