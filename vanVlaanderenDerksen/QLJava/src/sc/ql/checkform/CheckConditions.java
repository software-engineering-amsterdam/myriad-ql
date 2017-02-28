package sc.ql.checkform;

import java.util.HashMap;

import sc.ql.model.ConditionalBlock;
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
import sc.ql.model.form_elements.Question.Type;

public class CheckConditions implements NodeVisitor<Question.Type> {
	public HashMap<String, Question.Type> identifier_types;
	
	public CheckConditions(HashMap<String, Question.Type> identifier_types) {
		this.identifier_types = identifier_types;
		
		System.out.println(identifier_types);
	}
	
	@Override
	public Question.Type visit(ConditionalBlock conditional_block) throws Exception {
		Type type = conditional_block.getExpression().accept(this);
		System.out.println("ConditionalBlock Type => "+ type);
		
		if (type != Question.Type.BOOLEAN) {
			throw new Exception("Condition on line  is not of type Boolean.");
		}
		
		return type;
	}

	@Override
	public Question.Type visit(IfStatement if_statement) throws Exception {
		for (ConditionalBlock conditional_block : if_statement.getConditionalBlocks()) {
			conditional_block.accept(this);
			System.out.println("IfStatement");
		}
		
		return null;
	}
	
	@Override
	public Question.Type visit(NotExpression not_expression) {
		not_expression.getExpression();
		
		
		System.out.println("NotExpression");
		System.out.println(not_expression);
		
		return null;
	}

	@Override
	public Question.Type visit(OpExpression op_expression) throws Exception {
		try {
			op_expression.getLeft().accept(this);
			op_expression.getRight().accept(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("OpExpression");
		System.out.println(op_expression.getOperator());
		
		return null;
	}

	@Override
	public Question.Type visit(AtomBoolean atom_boolean) {
		// TODO Auto-generated method stub
		System.out.println("AtomBoolean");
		
		return null;
	}

	@Override
	public Question.Type visit(AtomFloat atom_float) {
		// TODO Auto-generated method stub
		System.out.println("AtomFloat");
		
		return null;
	}

	@Override
	public Question.Type visit(AtomId atom_id) {
		System.out.println("AtomId => "+ this.identifier_types.get(atom_id.getValue()));
		return this.identifier_types.get(atom_id.getValue());
	}

	@Override
	public Question.Type visit(AtomInteger atom_integer) {
		// TODO Auto-generated method stub
		System.out.println("AtomInteger");
		
		return null;
	}

	@Override
	public Question.Type visit(AtomMoney atom_money) {
		// TODO Auto-generated method stub
		System.out.println("AtomMoney");
		
		return null;
	}

	@Override
	public Question.Type visit(AtomString atom_string) {
		// TODO Auto-generated method stub
		System.out.println("AtomString");
		
		return null;
	}

	@Override
	public Question.Type visit(Question question) { return null; }
}