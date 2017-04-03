package QL.semantic;

import QL.ast.*;
import QL.ast.type.Type;
import QL.message.Error;
import QL.message.Warning;

import java.util.HashMap;
import java.util.Map;

/**
 * VerifyQuestions checks for
 * <li> duplicated questions
 * <li> duplicated labels
 */
public class VerifyQuestions implements FormVisitor {
	
	private final Environment environment;
	private final Map<String, String> labelName;
	
	VerifyQuestions(Environment environment) {
		this.environment = environment;
		labelName = new HashMap<>();
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
		checkDuplicateId(question.getName(), question.getType(), question.getLine());
		checkDuplicateLabel(question.getLabel(), question.getName(), question.getLine());
	}

	@Override
	public void visit(ComputedQuestion question) {
		checkDuplicateId(question.getName(), question.getType(), question.getLine());
		checkDuplicateLabel(question.getLabel(), question.getName(), question.getLine());
	}

	private void addLabel(String label, String name) {
		labelName.put(label, name);
	}

	private boolean labelExists(String label) {
		return labelName.containsKey(label);
	}

	private void checkDuplicateLabel(String label, String name, int line) {
		if (labelExists(label)) {
		    environment.addMessage(new Warning("The question: " + label +
                    " exists twice in the questionnaire", line));
		}
		addLabel(label, name);
	}

	private void checkDuplicateId(String name, Type type, int line) {

		if (environment.getReferenceTable().nameExists(name)) {
			environment.addMessage(new Error("The question " + name + " cannot be added, because it is "
					+ "already defined", line));
		}
		environment.getReferenceTable().addReference(name, type);
	}
}
