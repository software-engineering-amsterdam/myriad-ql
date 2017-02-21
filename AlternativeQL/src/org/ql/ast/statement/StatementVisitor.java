package org.ql.ast.statement;

public interface StatementVisitor<T> {
    T visit(IfThen ifThen) throws Throwable;
    T visit(IfThenElse ifThenElse) throws Throwable;
    T visit(Question question) throws Throwable;
}
