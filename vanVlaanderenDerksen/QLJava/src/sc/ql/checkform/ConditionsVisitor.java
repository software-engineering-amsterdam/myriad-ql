package sc.ql.checkform;

import java.util.Map;

import sc.ql.model.ConditionalBlock;
import sc.ql.model.NodeVisitor;
import sc.ql.model.expressions.CalcExpression;
import sc.ql.model.expressions.NotExpression;
import sc.ql.model.expressions.BinaryExpression;
import sc.ql.model.expressions.literals.BooleanLiteral;
import sc.ql.model.expressions.literals.IdLiteral;
import sc.ql.model.expressions.literals.IntegerLiteral;
import sc.ql.model.expressions.literals.AtomMoney;
import sc.ql.model.expressions.literals.StringLiteral;
import sc.ql.model.form_elements.IfStatement;
import sc.ql.model.form_elements.Question;
import sc.ql.model.form_elements.Question.Type;

public class ConditionsVisitor implements ExpressionVisitor<Question.Type> {
	public Map<String, Question.Type> identifierTypes;
	
	public ConditionsVisitor(Map<String, Question.Type> identifier_types) {
		this.identifierTypes = identifierTypes;
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
	public Question.Type visit(BinaryExpression op_expression) throws Exception {
		Type left_side = op_expression.getLeft().accept(this);
		Type right_side = op_expression.getRight().accept(this);
		String operator = op_expression.getOperator();
		
		if (operator.matches("<|<=|>|>=") && left_side != right_side && (left_side != Type.INTEGER || left_side != Type.MONEY)) {
			throw new Exception("Both sides of the "+operator+" operator on line "+op_expression.getLineNumber()+" needs to be of the same numeric type.");
		}
		else if(operator.matches("==|!=") && left_side != right_side) {
			throw new Exception("Both sides of the "+operator+" operator on line "+op_expression.getLineNumber()+" needs to be of the same expression/type.");
		}
		else if (operator.equals("&&|||") && left_side != right_side && left_side != Type.BOOLEAN) {
			throw new Exception("Both sides of the "+operator+" operator on line "+op_expression.getLineNumber()+" needs to be a Boolean expression/type.");
		}
		else if (!operator.matches("<|<=|>|>=|==|!=|&&|\\|\\||")) {
			throw new Exception("Operator "+operator+" on line "+op_expression.getLineNumber()+" is not permitted, only Boolean expressions are allowed.");
		}
		
		return Type.BOOLEAN;
	}
	
	@Override
	public Question.Type visit(CalcExpression calc_expression) throws Exception {
		Type left_side  = calc_expression.getLeft().accept(this);
		Type right_side = calc_expression.getRight().accept(this);
		String operator = calc_expression.getOperator();
		
		if (operator.matches("\\+|-|\\*|/") && left_side != right_side && (left_side != Type.INTEGER || left_side != Type.MONEY)) {
			throw new Exception("Both sides of the "+operator+" operator on line "+calc_expression.getLineNumber()+" needs to be of the same numeric type.");
		} 
		
		return left_side;
	}

	@Override
	public Question.Type visit(BooleanLiteral atom_boolean) {
		return Type.BOOLEAN;
	}

	@Override
	public Question.Type visit(IdLiteral atom_id) {
		return this.identifier_types.get(atom_id.getValue());
	}

	@Override
	public Question.Type visit(IntegerLiteral atom_integer) {
		return Type.INTEGER;
	}

	@Override
	public Question.Type visit(AtomMoney atom_money) {
		return Type.MONEY;
	}

	@Override
	public Question.Type visit(StringLiteral atom_string) {
		return Type.STRING;
	}

	@Override
	public Question.Type visit(Question question) throws Exception { 
		Type type = question.getExpression() != null ? question.getExpression().accept(this) : question.getType();
		
		if (type != question.getType()) {
			throw new Exception("Question '"+question.getId().getValue()+"' on line "+question.getLineNumber()+" has a different type than its expression.");
		}
		
		return type; 
	}
}