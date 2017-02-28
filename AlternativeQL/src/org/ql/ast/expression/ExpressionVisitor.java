package org.ql.ast.expression;

import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;

public interface ExpressionVisitor<T, C> {
    T visit(Negation node, C context);
    T visit(Product node, C context);
    T visit(Increment node, C context);
    T visit(Subtraction node, C context);
    T visit(NotEqual node, C context);
    T visit(LogicalAnd node, C context);
    T visit(LowerThan node, C context);
    T visit(GreaterThanOrEqual node, C context);
    T visit(Division node, C context);
    T visit(Parameter node, C context);
    T visit(Group node, C context);
    T visit(Addition node, C context);
    T visit(GreaterThan node, C context);
    T visit(Decrement node, C context);
    T visit(Equals node, C context);
    T visit(LowerThanOrEqual node, C context);
    T visit(LogicalOr node, C context);
    T visit(BooleanLiteral node, C context);
    T visit(DecimalLiteral node, C context);
    T visit(IntegerLiteral node, C context);
    T visit(StringLiteral node, C context);
}
