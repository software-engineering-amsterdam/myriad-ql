package ASTnodes.visitors;

import ASTnodes.Form;
import ASTnodes.expressions.binaries.equality.*;
import ASTnodes.expressions.binaries.logic.*;
import ASTnodes.expressions.binaries.numerical.*;
import ASTnodes.expressions.literals.*;
import ASTnodes.sections.*;
import ASTnodes.types.*;
import ASTnodes.expressions.unaries.*;

/**
 * Created by LGGX on 09-Feb-17.
 */

public interface AllVisitors<T> {

    T visit(Form structure);

    T visit(Question section);
    T visit(ExpressionQuestion section);
    T visit(IfStatement section);

    T visit(BooleanType type);
    T visit(StringType type);
    T visit(IntegerType type);
    T visit(MoneyType type);

    T visit(BOOLEAN literal);
    T visit(INTEGER literal);
    T visit(STRING literal);
    T visit(IDENTIFIER literal);
    T visit(MONEY literal);

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
