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
			page.accept(this);
		}
	}

	@Override
	public void visit(Page page) {
		for (Section section : page.getSections()) {
			section.accept(this);
		}
	}

	@Override
	public void visit(Section section) {
		for (Question question : section.getQuestions()) {
			question.accept(this);
		}		
	}

	@Override
	public void visit(Question question) {
		
		check(question.getName(), question.getLine());
		
		environment.setCovered(question.getName());
	}

	@Override
	public void visit(QuestionWithWidget question) {
		
		check(question.getName(), question.getLine());
		
		environment.setCovered(question.getName());
	}
	
	private void check(String name, int line) {
		
		if (!environment.presentInQL(name)) {
			environment.addMessage(new Error("The variable " + name +
					" appears in the QLS, but does not exist in QL", line));
		}

		if (environment.isCovered(name)) {
			environment.addMessage(new Error("The variable " + name +
			" is already defined in the QLS", line));
		}
	}

	@Override
	public void visit(PageWithDefault page) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(SectionWithDefault section) {
		// TODO Auto-generated method stub
		
	}

}
