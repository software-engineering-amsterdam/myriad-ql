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
		
		visit(question.getType());
		
	}
	
	public void visit(Type type) {
		// TODO Auto-generated method stub
	}

	public void visit(Statement statement) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Atom atom) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Expression expression) {
		// TODO Auto-generated method stub
		
	}
	
}
