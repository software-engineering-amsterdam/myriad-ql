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
		visit(form.getBlock());	
	}
	
	public void visit(Block block) {
		
		// TODO generalize
		List<Question> questions = block.getQuestions();		
		for (Question question : questions) {
			visit(question);
		}
		
		List<Statement> statements = block.getStatements();
		for (Statement statement : statements) {
			visit(statement);
		}
	}
	
	public void visit(Question question) {
		
		System.out.println(question.getVariable());
		
		visit(question.getType());
		
	}
	
	public void visit(Statement statement) {
		
		visit(statement.getExpression());
		visit(statement.getBlock()); // TODO circulair dependencies?
	}
	
	// TODO for types, atoms, expression can we only visit the general classes
	public void visit(Type type) {	
		type.accept(this);		
	}
	
	public void visit(BinaryExpression expression) {
		
		visit(expression.getLhs());
		visit(expression.getRhs());
	}
	

	public void visit(Atom atom) {
		// Your in the leave finished!
		System.out.println("LEAF");
		
	}

	public void visit(UnaryExpression expression) {
		
		visit(expression.getLhs());
		
	}
	
}
