package org.ql.ast.expression;

import org.ql.ast.expression.relational.*;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.*;

public interface ExpressionVisitor<T> {
    T visit(Negation node) throws Throwable;
    T visit(Product node) throws Throwable;
    T visit(Increment node) throws Throwable;
    T visit(Subtraction node) throws Throwable;
    T visit(NotEqual node) throws Throwable;
    T visit(LogicalAnd node) throws Throwable;
    T visit(LowerThan node) throws Throwable;
    T visit(GreaterThanOrEqual node) throws Throwable;
    T visit(Division node) throws Throwable;
    T visit(Parameter node) throws Throwable;
    T visit(Group node) throws Throwable;
    T visit(Addition node) throws Throwable;
    T visit(GreaterThan node) throws Throwable;
    T visit(Decrement node) throws Throwable;
    T visit(Equals node) throws Throwable;
    T visit(LowerThanOrEqual node) throws Throwable;
    T visit(LogicalOr node) throws Throwable;
    T visit(BooleanLiteral node) throws Throwable;
    T visit(DecimalLiteral node) throws Throwable;
    T visit(IntegerLiteral node) throws Throwable;
    T visit(StringLiteral node) throws Throwable;
}
