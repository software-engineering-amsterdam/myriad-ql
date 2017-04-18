package sc.ql.model.visitors;

import sc.ql.model.Form;

public interface FormVisitor<T> {
	public T visit(Form form);
}
