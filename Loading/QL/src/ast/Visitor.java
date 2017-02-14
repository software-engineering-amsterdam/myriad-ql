package ast;

import java.util.List;

import ast.atom.*;
import ast.expression.*;
import ast.type.*;

public class Visitor {
	
	public void visit(Form form) {
		
		// TODO or getBlock().accept()  // TODO violates the law of Demeter
		// Is it correct do you have an accept
		// method in getBlock if you don't use it?
		form.getBlock().accept(this);	
	}
	
	public void visit(Block block) {
		
		// TODO generalize
		List<Question> questions = block.getQuestions();		
		for (Question question : questions) {
			question.accept(this);
		}
		
		List<Statement> statements = block.getStatements();
		for (Statement statement : statements) {
			statement.accept(this);
		}
	}
	
	public void visit(Question question) {
		
		System.out.println(question.getVariable());
		
		question.getType().accept(this);
		
	}
	
	public void visit(Statement statement) {
		
		statement.getExpression().accept(this);
		statement.getBlock().accept(this);
//		visit(statement.getBlock()); // TODO circular dependencies?
	}
	
	// TODO for types, atoms, expression can we only visit the general classes
	public void visit(Type type) {	
		type.accept(this);		
	}
	
	public void visit(BinaryExpression binaryExpression) {
		binaryExpression.getLhs().accept(this);
		binaryExpression.getRhs().accept(this);	
	}

	public void visit(UnaryExpression unaryExpression) {
		unaryExpression.getLhs().accept(this);
		
	}

	public void visit(BoolAtom boolAtom) {
		System.out.println(boolAtom.getValue());
	}
	
	public void visit(DateAtom dateAtom) {
		System.out.println(dateAtom.getValue());
	}
	
	public void visit(DecimalAtom decimalAtom) {
		System.out.println(decimalAtom.getValue());
	}
	
	public void visit(IntegerAtom integerAtom) {
		System.out.println(integerAtom.getValue());
	}
	
	public void visit(StringAtom stringAtom) {
		System.out.println(stringAtom.getValue());
	}

// TODO really not needed
//	public void visit(AddExpression addExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(AndExpression andExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(DivExpression divExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(GEqExpression gEqExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(GExpression gExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(LEqExpression lEqExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(LExpression lExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(MinusExpression minusExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(MulExpression mulExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(NEqExpression nEqExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(NotExpression notExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(OrExpression orExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(PlusExpression plusExpression) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void visit(SubExpression subExpression) {
//		// TODO Auto-generated method stub
//		
//	}
}
