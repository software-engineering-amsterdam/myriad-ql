package org.ql.evaluator;

import org.ql.ast.Expression;
import org.ql.ast.expression.ExpressionVisitor;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;
import org.ql.evaluator.value.*;

public class Evaluator implements ExpressionVisitor<Value, Void> {

    @Override
    public Value visitProduct(Product node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.product(right);
    }

    @Override
    public Value visitIncrement(Increment node, Void context) {
        Value value = node.getExpression().accept(this, context);

        return value.increment();
    }

    @Override
    public Value visitSubtraction(Subtraction node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.subtraction(right);
    }

    @Override
    public Value visitDivision(Division node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.division(right);
    }

    @Override
    public Value visitParameter(Parameter node, Void context) {
        return null;
    }

    @Override
    public Value visitGroup(Group node, Void context) {
        return node.getExpression().accept(this, context);
    }

    @Override
    public Value visitAddition(Addition node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.addition(right);
    }

    @Override
    public Value visitDecrement(Decrement node, Void context) {
        Value value = node.getExpression().accept(this, context);

        return value.decrement();
    }

    @Override
    public Value visitGreaterThan(GreaterThan node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.greaterThan(right);
    }

    @Override
    public Value visitNegation(Negation node, Void context) {
        Value value = node.getExpression().accept(this, context);

        return value.negation();
    }

    @Override
    public Value visitNotEqual(NotEqual node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.notEqual(right);
    }

    @Override
    public Value visitAnd(LogicalAnd node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.and(right);
    }

    @Override
    public Value visitLowerThan(LowerThan node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.lowerThan(right);
    }

    @Override
    public Value visitGreaterThanOrEqual(GreaterThanOrEqual node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.greaterThanOrEqual(right);
    }

    @Override
    public Value visitEquals(Equals node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.equal(right);
    }

    @Override
    public Value visitLowerThanOrEqual(LowerThanOrEqual node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.lowerThanOrEqual(right);
    }

    @Override
    public Value visitOr(LogicalOr logicalOr, Void context) {
        Value left = logicalOr.getLeft().accept(this, context);
        Value right = logicalOr.getRight().accept(this, context);

        return left.or(right);
    }

    @Override
    public BooleanValue visitBoolean(BooleanLiteral booleanLiteral, Void context) {
        return new BooleanValue(booleanLiteral.getValue());
    }

    @Override
    public DecimalValue visitDecimal(DecimalLiteral decimalLiteral, Void context) {
        return new DecimalValue(decimalLiteral.getValue());
    }

    @Override
    public IntegerValue visitInteger(IntegerLiteral integerLiteral, Void context) {
        return new IntegerValue(integerLiteral.getValue());
    }

    @Override
    public StringValue visitString(StringLiteral stringLiteral, Void context) {
        return new StringValue(stringLiteral.getValue());
    }
}
