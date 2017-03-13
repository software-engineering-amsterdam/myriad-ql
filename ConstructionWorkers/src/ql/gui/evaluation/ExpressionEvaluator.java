/**
 * ExpressionValueChecker.java.
 */

package ql.gui.evaluation;

import ql.astnodes.expressions.binaries.equality.*;
import ql.astnodes.expressions.binaries.logic.AND;
import ql.astnodes.expressions.binaries.logic.OR;
import ql.astnodes.expressions.binaries.numerical.Addition;
import ql.astnodes.expressions.binaries.numerical.Division;
import ql.astnodes.expressions.binaries.numerical.Multiplication;
import ql.astnodes.expressions.binaries.numerical.Subtraction;
import ql.astnodes.expressions.literals.*;
import ql.astnodes.expressions.unaries.Negation;
import ql.astnodes.expressions.unaries.Negative;
import ql.astnodes.expressions.unaries.Parentheses;
import ql.astnodes.expressions.unaries.Positive;
import ql.visitorinterfaces.ExpressionVisitor;
import ql.gui.formenvironment.Context;
import ql.gui.formenvironment.values.*;

public class ExpressionEvaluator implements ExpressionVisitor<Value>{

    private final Context context;

    public ExpressionEvaluator(Context values) {
        this.context = values;
    }

    @Override
    public Value visit(Parentheses parenthesesExpression) {
        return parenthesesExpression.getExpression().accept(this);
    }

    @Override
    public Value visit(Negation negationExpression) {
        Value valueNegationExpression = negationExpression.getExpression().accept(this);
        return valueNegationExpression.negation();
    }

    @Override
    public Value visit(Negative negativeExpression) {
        Value negativeExpressionValue = negativeExpression.getExpression().accept(this);
        return negativeExpressionValue.negative();
    }

    @Override
    public Value visit(Positive positiveExpression) {
        Value positiveExpressionValue = positiveExpression.getExpression().accept(this);
        return positiveExpressionValue.positive();
    }

    @Override
    public Value visit(AND andExpression) {
        Value left = andExpression.getLeft().accept(this);
        Value right = andExpression.getRight().accept(this);
        return left.and(right);
    }

    @Override
    public Value visit(OR orExpression) {
        Value left = orExpression.getLeft().accept(this);
        Value right = orExpression.getRight().accept(this);
        return left.or(right);
    }

    @Override
    public Value visit(Addition additionExpression) {
        Value left = additionExpression.getLeft().accept(this);
        Value right = additionExpression.getRight().accept(this);
        return left.addition(right);
    }

    @Override
    public Value visit(Subtraction subtractionExpression) {
        Value left = subtractionExpression.getLeft().accept(this);
        Value right = subtractionExpression.getRight().accept(this);
        return left.subtraction(right);
    }

    @Override
    public Value visit(Multiplication multiplicationExpression) {
        Value left = multiplicationExpression.getLeft().accept(this);
        Value right = multiplicationExpression.getRight().accept(this);
        return left.multiplication(right);
    }

    @Override
    public Value visit(Division divisionExpression) {
        Value left = divisionExpression.getLeft().accept(this);
        Value right = divisionExpression.getRight().accept(this);
        return left.division(right);
    }

    @Override
    public Value visit(EQ eqExpression) {
        Value left = eqExpression.getLeft().accept(this);
        Value right = eqExpression.getRight().accept(this);
        return left.eq(right);
    }

    @Override
    public Value visit(NEQ neqExpression) {
        Value left = neqExpression.getLeft().accept(this);
        Value right = neqExpression.getRight().accept(this);
        return left.neq(right);
    }

    @Override
    public Value visit(GT gtExpression) {
        Value left = gtExpression.getLeft().accept(this);
        Value right = gtExpression.getRight().accept(this);
        return left.gt(right);
    }

    @Override
    public Value visit(LT ltExpression) {
        Value left = ltExpression.getLeft().accept(this);
        Value right = ltExpression.getRight().accept(this);
        return left.lt(right);
    }

    @Override
    public Value visit(GTEQ gteqExpression) {
        Value left = gteqExpression.getLeft().accept(this);
        Value right = gteqExpression.getRight().accept(this);
        return left.gteq(right);
    }

    @Override
    public Value visit(LTEQ lteqExpression) {
        Value left = lteqExpression.getLeft().accept(this);
        Value right = lteqExpression.getRight().accept(this);
        return left.lteq(right);
    }

    @Override
    public Value visit(Identifier identifierLiteral) {
        return context.getValue(identifierLiteral.getName());
    }

    @Override
    public Value visit(MyInteger myIntegerLiteral) {
        return new IntegerValue(myIntegerLiteral.getValue());
    }

    @Override
    public Value visit(Money moneyLiteral) {
        return new MoneyValue(moneyLiteral.getValue());
    }

    @Override
    public Value visit(MyString myStringLiteral) {
        return new StringValue(myStringLiteral.getValue());
    }

    @Override
    public Value visit(MyBoolean myBooleanLiteral) {
        return new BooleanValue(myBooleanLiteral.getValue());
    }
}
