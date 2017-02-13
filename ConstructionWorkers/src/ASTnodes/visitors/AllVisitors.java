package ASTnodes.visitors;

import ASTnodes.Form;
import ASTnodes.expressions.binaries.equality.*;
import ASTnodes.expressions.binaries.logic.OR;
import ASTnodes.expressions.binaries.numerical.Addition;
import ASTnodes.expressions.binaries.numerical.Division;
import ASTnodes.expressions.binaries.numerical.Multiplication;
import ASTnodes.expressions.binaries.numerical.Subtraction;
import ASTnodes.expressions.literals.IDENTIFIER;
import ASTnodes.sections.Question;
import ASTnodes.sections.ExpressionQuestion;
import ASTnodes.sections.IfStatement;
import ASTnodes.types.BooleanType;
import ASTnodes.types.IntegerType;
import ASTnodes.types.StringType;
import ASTnodes.expressions.literals.INTEGER;
import ASTnodes.expressions.literals.BOOLEAN;
import ASTnodes.expressions.literals.STRING;
import ASTnodes.expressions.unaries.Parenthesis;
import ASTnodes.expressions.unaries.Negation;
import ASTnodes.expressions.unaries.Negative;
import ASTnodes.expressions.unaries.Positive;
import ASTnodes.expressions.binaries.logic.AND;

/**
 * Created by LGGX on 09-Feb-17.
 */

public interface AllVisitors<T, U> {

    T visit(Form structure, U context);

    T visit(Question section, U context);
    T visit(ExpressionQuestion section, U context);
    T visit(IfStatement section, U context);

    T visit(BooleanType type, U context);
    T visit(StringType type, U context);
    T visit(IntegerType type, U context);

    T visit(BOOLEAN literal, U context);
    T visit(INTEGER literal, U context);
    T visit(STRING literal, U context);
    T visit(IDENTIFIER literal, U context);

    T visit(Parenthesis expression, U context);
    T visit(Negation expression, U context);
    T visit(Negative expression, U context);
    T visit(Positive expression, U context);

    T visit(AND expression, U context);
    T visit(OR expression, U context);

    T visit(EQ expression, U context);
    T visit(NEQ expression, U context);
    T visit(GT expression, U context);
    T visit(LT expression, U context);
    T visit(GTEQ expression, U context);
    T visit(LTEQ expression, U context);

    T visit(Addition expression, U context);
    T visit(Subtraction expression, U context);
    T visit(Multiplication expression, U context);
    T visit(Division expression, U context);

}
