package QL.semantic;

import QL.Warning;
import QL.Error;
import QL.ast.*;
import QL.ast.type.Type;
import QL.ast.FormVisitor;
import QL.ast.Form;

// Checks for duplicated questions
// Duplicate Labels (warning)

public class VerifyQuestions implements FormVisitor {
	
	private Environment environment;
	
	public VerifyQuestions(Environment environment) {
		this.environment = environment;
	}


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
	public void visit(Statement statement) {
		statement.getBlock().accept(this); // TODO circulair dependencies?
	}

	@Override
	public void visit(IfElseStatement statement) {
		statement.getBlock().accept(this); // TODO circulair dependencies?
		statement.getElseBlock().accept(this);
	}
	
	@Override
	public void visit(Question question) {
		addVariableType(question.getVariable(), question.getType(), question.getLine());
		addLabel(question.getLabel(), question.getVariable(), question.getLine());
	}

	// TODO computed question
	@Override
	public void visit(ComputedQuestion question) {
		addVariableType(question.getVariable(), question.getType(), question.getLine());
		addLabel(question.getLabel(), question.getVariable(), question.getLine());
	}

	private void addVariableType(String variable, Type type, int line) {

		if (environment.variableExists(variable)) {
			environment.getFaults().add(new Error("The variable " + variable + " cannot be added, because it is "
					+ "already defined", line));
		}
		environment.addVariableType(variable, type);
	}
	
	// TODO better if it would print both line numbers
	private void addLabel(String label, String variableName, int line) {
		if (environment.labelExists(label)) {
			environment.getFaults().add(new Warning("The question: " + label + 
					" exists twice in the questionnaire", line));
		}
		environment.addLabel(label, variableName);
	}
}
