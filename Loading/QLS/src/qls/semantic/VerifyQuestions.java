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
		for (Page page : stylesheet.getPages()) {
			visit(page);
		}
	}

	@Override
	public void visit(Page page) {
		for (Section section : page.getSections()) {
			visit(section);
		}
	}

	@Override
	public void visit(Section section) {
		for (Question question : section.getQuestions()) {
			visit(question);
		}		
	}

	@Override
	// TODO check the logic here instead of in the environment
	public void visit(Question question) {
		
		if (!environment.presentInQL(question.getName())) {
			environment.addMessage(new Error("The variable " + question.getName() +
					" appears in the QLS, but does not exist in QL", question.getLine()));
		}

		if (environment.isCovered(question.getName())) {
			environment.addMessage(new Error("The variable " + question.getName() +
			" is already defined in the QLS", question.getLine()));
		}
		
		environment.setCovered(question.getName());
	}

	@Override
	public void visit(QuestionWithWidget question) {
		System.out.println(question.getName());
	}

}
