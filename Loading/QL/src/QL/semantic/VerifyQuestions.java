package QL.semantic;

import QL.ast.*;
import QL.ast.type.Type;
import QL.errorhandling.Error;
import QL.errorhandling.Warning;
import QL.ast.FormVisitor;
import QL.ast.Form;

import java.util.HashMap;
import java.util.Map;

/**
 * VerifyQuestions checks for
 * <li> duplicated questions
 * <li> duplicated labels
 */
public class VerifyQuestions implements FormVisitor {
	
	private Environment environment;
	private Map<String, String> labelVariable;
	
	public VerifyQuestions(Environment environment) {
		this.environment = environment;
		labelVariable = new HashMap<>();
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
		statement.getBlock().accept(this);
	}

	@Override
	public void visit(IfElseStatement statement) {
		statement.getBlock().accept(this);
		statement.getElseBlock().accept(this);
	}
	
	@Override
	public void visit(Question question) {
		checkVariableType(question.getVariable(), question.getType(), question.getLine());
		checkLabel(question.getLabel(), question.getVariable(), question.getLine());
	}

	@Override
	public void visit(ComputedQuestion question) {
		checkVariableType(question.getVariable(), question.getType(), question.getLine());
		checkLabel(question.getLabel(), question.getVariable(), question.getLine());
	}

	private void addLabel(String label, String variableName) {
		labelVariable.put(label, variableName);
	}

	private boolean labelExists(String label) {
		return labelVariable.containsKey(label);
	}

	private void checkLabel(String label, String variableName, int line) {
		if (labelExists(label)) {
			environment.getFaults().add(new Warning("The question: " + label + 
					" exists twice in the questionnaire", line));
		}
		addLabel(label, variableName);
	}


	private void checkVariableType(String variable, Type type, int line) {

		if (environment.variableExists(variable)) {
			environment.getFaults().add(new Error("The variable " + variable + " cannot be added, because it is "
					+ "already defined", line));
		}
		environment.addVariableType(variable, type);
	}
}
