package org.ql.ast.expression;

import org.ql.ast.expression.relational.*;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.*;

public interface Visitor<T> {
    T visit(Negation node);
    T visit(Product node);
    T visit(Increment node);
    T visit(Subtraction node);
    T visit(NotEqual node);
    T visit(LogicalAnd node);
    T visit(LowerThan node);
    T visit(GreaterThanOrEqual node);
    T visit(Division node);
    T visit(Parameter node);
    T visit(Group node);
    T visit(Addition node);
    T visit(GreaterThan node);
    T visit(Decrement node);
    T visit(Equals node);
    T visit(LowerThanOrEqual node);
    T visit(LogicalOr node);
    T visit(BooleanLiteral node);
    T visit(DecimalLiteral node);
    T visit(IntegerLiteral node);
    T visit(StringLiteral node);
}
