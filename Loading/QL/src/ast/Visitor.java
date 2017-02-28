package ast;

import java.util.List;

import ast.atom.*;
import ast.expression.*;
import ast.type.*;

// In order traversal
// TODO implements VisitorInterface 
public class Visitor {
	
	public void visit(Form form) {
		
		// TODO or getBlock().accept()  // TODO violates the law of Demeter
		// Is it correct do you have an accept
		// method in getBlock if you don't use it?
		form.getBlock().accept(this);
	}
	
	public void visit(Block block) {
		
		for (BlockItem blockItem : block.getBlockItems()) {
			blockItem.accept(this);
		}
	}
	
	public void visit(BlockItem blockItem) {	
		blockItem.accept(this);		
	}
	
	// TODO remove?
	public void visit(Question question) {	
		question.getType().accept(this);		
	}
	
	// TODO remove?
	public void visit(Statement statement) {		
		statement.getExpression().accept(this);
		statement.getBlock().accept(this); // TODO circulair dependencies?
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
	
	public void visit(IdExpression id) {
		System.out.println(id.getName());
	}

	public void visit(BoolAtom boolAtom) {
		System.out.println(boolAtom.getValue());
	}

	public void visit(IntegerAtom integerAtom) {
		System.out.println(integerAtom.getNumber());
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
