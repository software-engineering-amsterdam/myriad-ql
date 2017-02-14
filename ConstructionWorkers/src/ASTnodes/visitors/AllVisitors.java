/**
 * AllVisitors.java.
 */

package ASTnodes.visitors;

import ASTnodes.Form;
import ASTnodes.expressions.binaries.equality.*;
import ASTnodes.expressions.binaries.logic.*;
import ASTnodes.expressions.binaries.numerical.*;
import ASTnodes.expressions.literals.*;
import ASTnodes.expressions.literals.MyInteger;
import ASTnodes.statements.*;
import ASTnodes.types.*;
import ASTnodes.expressions.unaries.*;

public interface AllVisitors<T> {

    T visit(Form structure);

    T visit(SimpleQuestion statement);
    T visit(ComputedQuestion statement);
    T visit(IfStatement statement);

    T visit(BooleanType type);
    T visit(StringType type);
    T visit(IntegerType type);
    T visit(MoneyType type);

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
