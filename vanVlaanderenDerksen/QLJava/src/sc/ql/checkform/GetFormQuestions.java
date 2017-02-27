package sc.ql.checkform;

import java.util.ArrayList;
import java.util.List;

import sc.ql.model.ConditionalBlock;
import sc.ql.model.Form;
import sc.ql.model.FormElement;
import sc.ql.model.NodeVisitor;
import sc.ql.model.expressions.Expression;
import sc.ql.model.form_elements.IfStatement;
import sc.ql.model.form_elements.Question;

public class GetFormQuestions implements NodeVisitor<List<Question>> {
	
	@Override
	public List<Question> visit(Question question) {
		List<Question> questions = new ArrayList<Question>();
		questions.add(question);
		
		return questions;
	}

	@Override
	public List<Question> visit(ConditionalBlock conditional_block) {
		List<Question> questions = new ArrayList<Question>();
	
		for (FormElement form_element : conditional_block.getFormElements()) {
			questions.addAll(form_element.accept(this));
        }
		
		return questions;
	}

	@Override
	public List<Question> visit(IfStatement if_statement) {
		List<Question> questions = new ArrayList<Question>();
		
		for (ConditionalBlock conditional_block : if_statement.getConditionalBlocks()) {
			questions.addAll(conditional_block.accept(this));
        }
		
		for (FormElement form_element : if_statement.getFormElements()) {
			questions.addAll(form_element.accept(this));
        }
		
		return questions;
	}
	
	@Override
	public List<Question> visit(Form form) { return null; }

	@Override
	public List<Question> visit(Expression espression) { return null; }
}
