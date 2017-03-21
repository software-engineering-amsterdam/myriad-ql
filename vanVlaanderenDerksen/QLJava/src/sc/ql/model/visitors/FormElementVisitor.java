package sc.ql.model.visitors;

import sc.ql.model.form_elements.*;

public interface FormElementVisitor<T> {
	public T visit(Question question);
	public T visit(CalculatedQuestion question);
	public T visit(IfThenStatement statement);
	public T visit(IfThenElseStatement statement);
}
