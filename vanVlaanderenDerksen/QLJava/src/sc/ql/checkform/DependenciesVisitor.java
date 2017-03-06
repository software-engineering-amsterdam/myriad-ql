package sc.ql.checkform;

import java.util.ArrayList;
import java.util.List;

import sc.ql.model.ConditionalBlock;
import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;
import sc.ql.model.atoms.AtomBoolean;
import sc.ql.model.atoms.AtomId;
import sc.ql.model.atoms.AtomInteger;
import sc.ql.model.atoms.AtomMoney;
import sc.ql.model.atoms.AtomString;
import sc.ql.model.expressions.NotExpression;
import sc.ql.model.expressions.OpExpression;
import sc.ql.model.form_elements.IfStatement;
import sc.ql.model.form_elements.Question;

public class DependenciesVisitor implements NodeVisitor<List<String>> {
	private List<String> question_ids = new ArrayList<String>();
	
	@Override
	public List<String> visit(Question question) throws Exception {
		question_ids.add(question.getId().getValue());
		
		return question_ids;
	}

	@Override
	public List<String> visit(ConditionalBlock conditional_block) throws Exception {
		conditional_block.getExpression().accept(this);
		
		for (Node form_element : conditional_block.getFormElements()) {
			form_element.accept(this);
        }
		
		return question_ids;
	}

	@Override
	public List<String> visit(IfStatement if_statement) throws Exception {
		for (ConditionalBlock conditional_block : if_statement.getConditionalBlocks()) {
			conditional_block.accept(this);
        }
		
		for (Node form_element : if_statement.getFormElements()) {
			form_element.accept(this);
        }
		
		return question_ids;
	}

	@Override
	public List<String> visit(NotExpression not_expression) throws Exception {
		not_expression.getExpression().accept(this);
		
		return question_ids;
	}

	@Override
	public List<String> visit(OpExpression op_expression) throws Exception {
		op_expression.getLeft().accept(this);
		op_expression.getRight().accept(this);
		
		return question_ids;
	}

	@Override
	public List<String> visit(AtomBoolean atom_boolean) throws Exception {
		return question_ids;
	}

	@Override
	public List<String> visit(AtomId atom_id) throws Exception {		
		if (!question_ids.contains(atom_id.getValue())) {
			throw new Exception("The identifier '"+atom_id.getValue()+"' on line "+atom_id.getLineNumber()+" is not (yet) declared.");
		}
		
		return question_ids;
	}

	@Override
	public List<String> visit(AtomInteger atom_integer) throws Exception {
		return question_ids;
	}

	@Override
	public List<String> visit(AtomMoney atom_money) throws Exception {
		return question_ids;
	}

	@Override
	public List<String> visit(AtomString atom_string) throws Exception {
		return question_ids;
	}
}
