package sc.ql.model.visitors;

import sc.ql.model.form_elements.CalculatedQuestion;
import sc.ql.model.form_elements.Question;

public interface QuestionVisitor<T> {
	public T visit(Question question);
	public T visit(CalculatedQuestion question);
}
