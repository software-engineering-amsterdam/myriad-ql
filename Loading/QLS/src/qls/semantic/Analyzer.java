package qls.semantic;

import QL.ReferenceTable;
import QL.message.Message;
import QL.ui.StyleTable;
import qls.ast.Stylesheet;

import java.util.HashMap;
import java.util.List;

public class Analyzer {
	
	private final Environment environment;
	
	public Analyzer(ReferenceTable referenceTable) {
		this.environment = new Environment(referenceTable);
	}
	
	public StyleTable analyze(Stylesheet stylesheet) {
		
		VerifyQuestions verifyQuestions = new VerifyQuestions(environment);
		verifyQuestions.visit(stylesheet);

		VerifyTypes verifyTypes = new VerifyTypes(environment);
		verifyTypes.visit(stylesheet);

        // TODO implement
        return new StyleTable(new HashMap<>());

    }
	
	public List<Message> getMessages() {
		return environment.getMessages();
	}
}
