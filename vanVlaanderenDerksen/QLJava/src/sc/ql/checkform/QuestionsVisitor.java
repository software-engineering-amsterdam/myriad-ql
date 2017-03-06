package sc.ql.checkform;

import java.util.ArrayList;
import java.util.List;

import sc.ql.model.ConditionalBlock;
import sc.ql.model.FormElement;
import sc.ql.model.NodeVisitor;
import sc.ql.model.atoms.*;
import sc.ql.model.expressions.NotExpression;
import sc.ql.model.expressions.OpExpression;
import sc.ql.model.form_elements.IfStatement;
import sc.ql.model.form_elements.Question;

public class QuestionsVisitor implements NodeVisitor<List<Question>> {
	private List<Question> questions = new ArrayList<Question>();
	
	@Override
	public List<Question> visit(Question question) {
		questions.add(question);
		
		return questions;
	}

	@Override
	public List<Question> visit(ConditionalBlock conditional_block) throws Exception {
		for (FormElement form_element : conditional_block.getFormElements()) {
			questions.addAll(form_element.accept(this));
        }
		
		return questions;
	}

	@Override
	public List<Question> visit(IfStatement if_statement) throws Exception {
		for (ConditionalBlock conditional_block : if_statement.getConditionalBlocks()) {
			questions.addAll(conditional_block.accept(this));
        }
		
		for (FormElement form_element : if_statement.getFormElements()) {
			questions.addAll(form_element.accept(this));
        }
		
		return questions;
	}

	@Override
	public List<Question> visit(NotExpression not_expression) { return null; }

	@Override
	public List<Question> visit(OpExpression op_expression) { return null; }

	@Override
	public List<Question> visit(AtomBoolean atom_boolean) { return null; }

	@Override
	public List<Question> visit(AtomId atom_id) { return null; }

	@Override
	public List<Question> visit(AtomInteger atom_integer) { return null; }

	@Override
	public List<Question> visit(AtomMoney atom_money) { return null; }

	@Override
	public List<Question> visit(AtomString atom_string) { return null; }
}
