package sc.ql.model;

import sc.ql.model.expressions.Expression;
import sc.ql.model.form_elements.IfStatement;
import sc.ql.model.form_elements.Question;

public interface NodeVisitor<T> {
	public T visit(Form form);
	public T visit(Question question);
	public T visit(ConditionalBlock conditional_block);
	public T visit(IfStatement if_statement);
	public T visit(Expression expression);
	public T visit(Atom<T> atom);
}
