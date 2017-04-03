package qls.semantic;

import ql.message.Error;
import qls.ast.*;


/** VerifyQuestions checks if
 * <li> questions are not placed multiple times
 * <li> all questions of the ql program are placed in the QLS program
 * <li> there is no reference to questions that are not in the ql program
 *
 */
public class VerifyQuestions implements StylesheetVisitor {
	
	private final Environment environment;
	
	VerifyQuestions(Environment environment) {
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
			environment.addMessage(new Error("[QLS] The question " + name +
					" appears in the QLS, but does not exist in ql", line));
		}

		if (environment.isPresentQLS(name)) {
			environment.addMessage(new Error("[QLS] The question " + name +
			" is already defined in the QLS", line));
		}
	}

}
