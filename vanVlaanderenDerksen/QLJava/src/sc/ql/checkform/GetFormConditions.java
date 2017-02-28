package sc.ql.checkform;

import java.util.ArrayList;
import java.util.List;

import sc.ql.model.Atom;
import sc.ql.model.ConditionalBlock;
import sc.ql.model.Form;
import sc.ql.model.NodeVisitor;
import sc.ql.model.expressions.Expression;
import sc.ql.model.form_elements.IfStatement;
import sc.ql.model.form_elements.Question;

public class GetFormConditions implements NodeVisitor<List<Expression>> {

	@Override
	public List<Expression> visit(ConditionalBlock conditional_block) {
		List<Expression> expressions = new ArrayList<Expression>();
		expressions.addAll(conditional_block.getExpression().accept(this));
		
		return expressions;
	}

	@Override
	public List<Expression> visit(IfStatement if_statement) {
		List<Expression> expressions = new ArrayList<Expression>();
		
		for (ConditionalBlock conditional_block : if_statement.getConditionalBlocks()) {
			expressions.addAll(conditional_block.accept(this));
        }
		
		return expressions;
	}
	
	@Override
	public List<Expression> visit(Expression expression) { 
		List<Expression> expressions = new ArrayList<Expression>();
		//expressions.addAll(expression.accept(this));
		
		return expressions;
	}
	
	@Override
	public List<Expression> visit(Atom<List<Expression>> atom) {
		System.out.println("Test");
		System.out.println(atom);
		
		return null;
	}
	
	@Override
	public List<Expression> visit(Question question) { 
		List<Expression> expressions = new ArrayList<Expression>();		
		return expressions;
	}
	
	@Override
	public List<Expression> visit(Form form) { return null; }
}