/**
 * ExpressionVisitor.java.
 */

package QL.ASTnodes.visitors;

import QL.ASTnodes.expressions.binaries.equality.*;
import QL.ASTnodes.expressions.binaries.logic.*;
import QL.ASTnodes.expressions.binaries.numerical.*;
import QL.ASTnodes.expressions.literals.*;
import QL.ASTnodes.expressions.unaries.*;

public interface ExpressionVisitor<T> {
    T visit(MyBoolean literal);
    T visit(MyInteger literal);
    T visit(MyString literal);
    T visit(Identifier literal);
    T visit(Money literal);

    T visit(Parenthesis expression);
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
