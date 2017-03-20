package sc.ql.model.visitors;

import sc.ql.model.form_elements.IfThenElseStatement;
import sc.ql.model.form_elements.IfThenStatement;

public interface StatementVisitor<T> {
	public T visit(IfThenStatement statement);
	public T visit(IfThenElseStatement statement);
}
