/**
 * ExpressionVisitor.java.
 */

package ql.astnodes.visitors;

import ql.astnodes.expressions.binaries.equality.*;
import ql.astnodes.expressions.binaries.logic.*;
import ql.astnodes.expressions.binaries.numerical.*;
import ql.astnodes.expressions.literals.*;
import ql.astnodes.expressions.unaries.*;

public interface ExpressionVisitor<T> {
    T visit(MyBoolean literal);
    T visit(MyInteger literal);
    T visit(MyString literal);
    T visit(Identifier literal);
    T visit(Money literal);

    T visit(Parentheses expression);
    T visit(Negation expression);
    T visit(Negative expression);
    T visit(Positive expression);

    T visit(AND expression);
    T visit(OR expression);

    T visit(EQ expression);
    T visit(NEQ expression);
    T visit(GT expression);
    T visit(LT expression);
    T visit(GTEQ expression);
    T visit(LTEQ expression);

    T visit(Addition expression);
    T visit(Subtraction expression);
    T visit(Multiplication expression);
    T visit(Division expression);
}
