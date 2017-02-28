package org.ql.ast.statement;

public interface StatementVisitor<T, C> {
    T visit(IfThen ifThen, C context);
    T visit(IfThenElse ifThenElse, C context);
    T visit(Question question, C context);
}
