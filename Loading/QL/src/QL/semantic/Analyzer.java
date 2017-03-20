package QL.semantic;
import QL.message.Message;
import QL.ReferenceTable;
import QL.ast.Form;

import java.util.List;

public class Analyzer {

	private final Environment environment;

	public Analyzer() {
		this.environment = new Environment();
	}


    public ReferenceTable analyze(Form form) {
        VerifyQuestions verifyQuestions = new VerifyQuestions(environment);
        verifyQuestions.visit(form);

        VerifyExpressions verifyExpressions = new VerifyExpressions(environment);
        verifyExpressions.visit(form);

        CheckCyclicDependencies cyclicVisitor = new CheckCyclicDependencies(environment);
        cyclicVisitor.visit(form);

        return environment.getReferenceTable();
    }


    public List<Message> getMessages() {
    	return environment.getMessages();
    }
}
