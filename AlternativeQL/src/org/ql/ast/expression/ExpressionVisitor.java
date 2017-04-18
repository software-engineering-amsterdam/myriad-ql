package org.ql.ast.expression;

import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;

public interface ExpressionVisitor<T, C> {

    // arithmetic
    T visitProduct(Product node, C context);
    T visitIncrement(Increment node, C context);
    T visitSubtraction(Subtraction node, C context);
    T visitDivision(Division node, C context);
    T visitAddition(Addition node, C context);
    T visitDecrement(Decrement node, C context);

    // relational
    T visitNegation(Negation node, C context);
    T visitNotEqual(NotEqual node, C context);
    T visitAnd(LogicalAnd node, C context);
    T visitLowerThan(LowerThan node, C context);
    T visitGreaterThanOrEqual(GreaterThanOrEqual node, C context);
    T visitGroup(Group node, C context);
    T visitGreaterThan(GreaterThan node, C context);
    T visitEquals(Equals node, C context);
    T visitLowerThanOrEqual(LowerThanOrEqual node, C context);
    T visitOr(LogicalOr node, C context);

    // literals
    T visitBoolean(BooleanLiteral node, C context);
    T visitDecimal(DecimalLiteral node, C context);
    T visitInteger(IntegerLiteral node, C context);
    T visitString(StringLiteral node, C context);

    T visitParameter(Parameter node, C context);
}
