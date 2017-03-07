package sc.ql.model.form_elements;

import sc.ql.model.Node;
import sc.ql.model.NodeVisitor;
import sc.ql.model.atoms.AtomId;

public class Question implements Node {
	private final String question; 
	private final AtomId id;
	private final Type type;
	private final Node expression;
	private final Integer line_number;
	
	public enum Type {
		BOOLEAN, INTEGER, MONEY, STRING;
	}
	
	public Question(String question, AtomId id, Type type, Node expression, Integer line_number) {
		this.question = question;
		this.id = id;
		this.type = type;
		this.expression = expression;
		this.line_number = line_number;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public AtomId getId() {
		return this.id;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public Node getExpression() {
		return this.expression;
	}
	
	public Integer getLineNumber() {
		return this.line_number;
	}
	
	@Override
	public <T> T accept(NodeVisitor<T> visitor) throws Exception {
		return visitor.visit(this);
	}
}