package ast;

import java.util.List;

import ast.atom.*;
import ast.expression.*;
import ast.type.*;

// In order traversal
// TODO implements VisitorInterface
public abstract class InOrderVisitor implements FormVisitor {

	@Override
	public void visit(Form form) {
		form.getBlock().accept(this);
	}

	@Override
	public void visit(Block block) {
		
		for (BlockItem blockItem : block.getBlockItems()) {
			blockItem.accept(this);
		}
	}

	@Override
	public void visit(BlockItem blockItem) {
		blockItem.accept(this);
	}

	@Override
	public void visit(Question question) {
		// question.getType().accept(this);
	}

	@Override
	public void visit(Statement statement) {
		// statement.getExpression().accept(this);
		// statement.getBlock().accept(this); // TODO circulair dependencies?
	}

	// TODO computed question
	@Override
	public void visit(ComputedQuestion question) {
//		question.getType().accept(this);
	}


//	public void visit(BinaryExpression binaryExpression) {
//		binaryExpression.getLhs().accept(this);
//		binaryExpression.getRhs().accept(this);
//	}

//	public void visit(UnaryExpression unaryExpression) {
//		unaryExpression.getLhs().accept(this);
//	}
//
//	public void visit(IdExpression id) {
//		System.out.println(id.getName());
//	}


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
