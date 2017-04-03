package qls.semantic;

import QL.message.Error;
import qls.ast.*;

// you cannot place a single question multiple times.
// all questions of the QL program are placed by the QLS program.
// no references to questions that are not in the QL program
public class VerifyQuestions implements StylesheetVisitor {
	
	private final Environment environment;
	
	public VerifyQuestions(Environment environment) {
		this.environment = environment;
	}
	
	@Override
	public void visit(Stylesheet stylesheet) {
		for (Page page : stylesheet) {
			page.accept(this);
		}
	}

	@Override
	public void visit(Page page) {
		for (Section section : page) {
			section.accept(this);
		}
	}

	@Override
	public void visit(Section section) {
		for (Question question : section) {
			question.accept(this);
		}		
	}

	@Override
	public void visit(Question question) {
		
		check(question.getName(), question.getLine());
		
		environment.setPresentQLS(question.getName());
	}

	@Override
	public void visit(QuestionWithWidget question) {

		check(question.getName(), question.getLine());

		environment.setPresentQLS(question.getName());
	}

	private void check(String name, int line) {

		if (!environment.isPresentQL(name)) {
			environment.addMessage(new Error("The question " + name +
					" appears in the QLS, but does not exist in QL", line));
		}

		if (environment.isPresentQLS(name)) {
			environment.addMessage(new Error("The question " + name +
			" is already defined in the QLS", line));
		}
	}

}
