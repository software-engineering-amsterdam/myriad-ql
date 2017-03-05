package sc.ql.checkform;

import java.util.ArrayList;
import java.util.List;

import sc.ql.model.ConditionalBlock;
import sc.ql.model.FormElement;
import sc.ql.model.NodeVisitor;
import sc.ql.model.Atoms.AtomBoolean;
import sc.ql.model.Atoms.AtomFloat;
import sc.ql.model.Atoms.AtomId;
import sc.ql.model.Atoms.AtomInteger;
import sc.ql.model.Atoms.AtomMoney;
import sc.ql.model.Atoms.AtomString;
import sc.ql.model.expressions.NotExpression;
import sc.ql.model.expressions.OpExpression;
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
	public List<Question> visit(ConditionalBlock conditional_block) throws Exception {
		List<Question> questions = new ArrayList<Question>();
	
		for (FormElement form_element : conditional_block.getFormElements()) {
			questions.addAll(form_element.accept(this));
        }
		
		return questions;
	}

	@Override
	public List<Question> visit(IfStatement if_statement) throws Exception {
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
	public List<Question> visit(NotExpression not_expression) { return null; }

	@Override
	public List<Question> visit(OpExpression op_expression) { return null; }

	@Override
	public List<Question> visit(AtomBoolean atom_boolean) { return null; }

	@Override
	public List<Question> visit(AtomFloat atom_float) { return null; }

	@Override
	public List<Question> visit(AtomId atom_id) { return null; }

	@Override
	public List<Question> visit(AtomInteger atom_integer) { return null; }

	@Override
	public List<Question> visit(AtomMoney atom_money) { return null; }

	@Override
	public List<Question> visit(AtomString atom_string) { return null; }
}
