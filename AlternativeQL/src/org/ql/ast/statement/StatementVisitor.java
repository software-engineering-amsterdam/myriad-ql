package org.ql.ast.statement;

public interface StatementVisitor<T, C> {
    T visitIfThen(IfThen ifThen, C context);
    T visitIfThenElse(IfThenElse ifThenElse, C context);
    T visitQuestion(Question question, C context);
    T visitComputableQuestion(ComputableQuestion question, C context);
}
