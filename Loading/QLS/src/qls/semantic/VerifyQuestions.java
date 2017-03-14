package qls.semantic;

import qls.ast.Page;
import qls.ast.Question;
import qls.ast.Section;
import qls.ast.Stylesheet;
import qls.ast.StylesheetVisitor;

// you cannot place a single question multiple times.
// all questions of the QL program are placed by the QLS program.
// no references to questions that are not in the QL program
public class VerifyQuestions implements StylesheetVisitor {
	
	private Environment environment;
	
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
	
//	// TODO is this necessary?
//	@Override
//	public void visit(PageWithDefault pageWithDefault) {
//		for (Section section : pageWithDefault.getSections()) {
//			visit(section);
//		}	
//	}

	@Override
	public void visit(Section section) {
		for (Question question : section.getQuestions()) {
			visit(question);
		}		
	}
	
//	// TODO is this necessary?
//	@Override
//	public void visit(SectionWithDefault sectionWithDefault) {
//		for (Question question : sectionWithDefault.getQuestions()) {
//			visit(question);
//		}	
//		
//	}

	@Override
	// TODO check the logic here instead of in the environment
	public void visit(Question question) {
		environment.isCovered(question.getName(), question.getLine());
	}
	
//	// TODO is this necessary?
//	@Override
//	public void visit(QuestionWithWidget questionWithDefault) {
//		// TODO Auto-generated method stub
//		
//	}

}
