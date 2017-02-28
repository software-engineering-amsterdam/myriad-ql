package semantic;

import ast.*;
import ast.type.Type;
import ast.FormVisitor;
import ast.Form;

// Checks for duplicated questions
// Duplicate Labels (warning)
// TODO change name - Visitor does not add to the understanding
public class QuestionVisitor implements FormVisitor {
	
	private Environment environment;
	
	public QuestionVisitor(Environment environment) {
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
	public void visit(Question question) {
		addVariableType(question.getVariable(), question.getType(), question.getLine());
		addLabel(question.getLabel(), question.getVariable(), question.getLine());
	}

	@Override
	public void visit(Statement statement) {
		statement.getBlock().accept(this); // TODO circulair dependencies?
	}

	// TODO computed question
	@Override
	public void visit(ComputedQuestion question) {
		addVariableType(question.getVariable(), question.getType(), question.getLine());
		addLabel(question.getLabel(), question.getVariable(), question.getLine());
	}

	private void addVariableType(String variable, Type type, int line) {
		if (environment.variableExists(variable)) {
			throw new RuntimeException("The variable: " + variable + " on line " + line +
					" cannot be added, because it is already defined");
		}
		environment.addVariableType(variable, type);
	}

	private void addLabel(String label, String variableName, int line) {
		if (environment.labelExists(label)) {
			// TODO WARNING not throw
			System.out.println("The question \" "  + label
					+ " \"on line " + line + " exists twice in the questionnaire.");
		}
		environment.addLabel(label, variableName);
	}
}
