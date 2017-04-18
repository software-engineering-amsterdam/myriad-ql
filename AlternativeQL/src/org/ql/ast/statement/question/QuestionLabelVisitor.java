package org.ql.ast.statement.question;

public interface QuestionLabelVisitor<T, C> {
    T visitQuestionLabel(QuestionLabel questionLabel, C context);
}
