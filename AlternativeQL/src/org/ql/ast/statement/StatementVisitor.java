package org.ql.ast.statement;

public interface StatementVisitor<T> {
    T visit(IfThen ifThen);

    T visit(IfThenElse ifThenElse);

    T visit(Question question);
}
