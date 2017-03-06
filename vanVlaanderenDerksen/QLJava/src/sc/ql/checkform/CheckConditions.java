package sc.ql.checkform;

import java.util.HashMap;

import sc.ql.model.ConditionalBlock;
import sc.ql.model.NodeVisitor;
import sc.ql.model.atoms.*;
import sc.ql.model.expressions.NotExpression;
import sc.ql.model.expressions.OpExpression;
import sc.ql.model.form_elements.IfStatement;
import sc.ql.model.form_elements.Question;
import sc.ql.model.form_elements.Question.Type;

public class CheckConditions implements NodeVisitor<Question.Type> {
	public HashMap<String, Question.Type> identifier_types;
	
	public CheckConditions(HashMap<String, Question.Type> identifier_types) {
		this.identifier_types = identifier_types;
	}
	
	@Override
	public Question.Type visit(ConditionalBlock conditional_block) throws Exception {
		Type type = conditional_block.getExpression().accept(this);
		
		if (type != Type.BOOLEAN) {
			throw new Exception("Condition on line "+conditional_block.getLineNumber()+" is not of type Boolean.");
		}
		
		return type;
	}

	@Override
	public Question.Type visit(IfStatement if_statement) throws Exception {
		for (ConditionalBlock conditional_block : if_statement.getConditionalBlocks()) {
			conditional_block.accept(this);
		}
		
		return null;
	}
	
	@Override
	public Question.Type visit(NotExpression not_expression) throws Exception {
		Type type = not_expression.getExpression().accept(this);
		
		if (type != Type.BOOLEAN) {
			throw new Exception("Expression negation on line "+not_expression.getLineNumber()+" is not of type Boolean.");
		}
		
		return type;
	}

	@Override
	public Question.Type visit(OpExpression op_expression) throws Exception {
		Type left_side = op_expression.getLeft().accept(this);
		Type right_side = op_expression.getRight().accept(this);
		String operator = op_expression.getOperator();
		
		if (operator.matches("<|<=|>|>=|==|!=") && left_side != right_side) {
			throw new Exception("Both sides of the "+operator+" operator on line "+op_expression.getLineNumber()+" needs to be of the same expression/type.");
		}
		else if ((operator.equals("&&") && left_side != Type.BOOLEAN && right_side != Type.BOOLEAN) ||
				 (operator.equals("||") && (left_side != Type.BOOLEAN || right_side != Type.BOOLEAN)) ) {
			throw new Exception("Both sides of the "+operator+" operator on line "+op_expression.getLineNumber()+" needs to be a Boolean expression/type.");
		}
		else if (!operator.matches("<|<=|>|>=|==|!=|&&|\\|\\||")) {
			throw new Exception("Operator "+operator+" on line "+op_expression.getLineNumber()+" is not permitted, only Boolean expressions are allowed.");
		}
		
		return Type.BOOLEAN;
	}

	@Override
	public Question.Type visit(AtomBoolean atom_boolean) {
		return Type.BOOLEAN;
	}

	@Override
	public Question.Type visit(AtomFloat atom_float) {
		return Type.FLOAT;
	}

	@Override
	public Question.Type visit(AtomId atom_id) {
		return this.identifier_types.get(atom_id.getValue());
	}

	@Override
	public Question.Type visit(AtomInteger atom_integer) {
		return Type.INTEGER;
	}

	@Override
	public Question.Type visit(AtomMoney atom_money) {
		return Type.MONEY;
	}

	@Override
	public Question.Type visit(AtomString atom_string) {
		return Type.STRING;
	}

	@Override
	public Question.Type visit(Question question) { return null; }
}