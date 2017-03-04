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
    public Value visit(Product node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getLeft().accept(this, context);

        return left.product(right);
    }

    @Override
    public Value visit(Increment node, Void context) {
        Value value = node.getExpression().accept(this, context);

        return value.increment();
    }

    @Override
    public Value visit(Subtraction node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getLeft().accept(this, context);

        return left.subtraction(right);
    }

    @Override
    public Value visit(Division node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getLeft().accept(this, context);

        return left.division(right);
    }

    @Override
    public Value visit(Parameter node, Void context) {
        return null;
    }

    @Override
    public Value visit(Group node, Void context) {
        return node.getExpression().accept(this, context);
    }

    @Override
    public Value visit(Addition node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getLeft().accept(this, context);

        return left.addition(right);
    }

    @Override
    public Value visit(Decrement node, Void context) {
        Value value = node.getExpression().accept(this, context);

        return value.decrement();
    }

    @Override
    public Value visit(GreaterThan node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getLeft().accept(this, context);

        return left.greaterThan(right);
    }

    @Override
    public Value visit(Negation node, Void context) {
        Value value = node.getExpression().accept(this, context);

        return value.negation();
    }

    @Override
    public Value visit(NotEqual node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.notEqual(right);
    }

    @Override
    public Value visit(LogicalAnd node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.and(right);
    }

    @Override
    public Value visit(LowerThan node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.lowerThan(right);
    }

    @Override
    public Value visit(GreaterThanOrEqual node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.greaterThanOrEqual(right);
    }

    @Override
    public Value visit(Equals node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.equal(right);
    }

    @Override
    public Value visit(LowerThanOrEqual node, Void context) {
        Value left = node.getLeft().accept(this, context);
        Value right = node.getRight().accept(this, context);

        return left.lowerThanOrEqual(right);
    }

    @Override
    public Value visit(LogicalOr logicalOr, Void context) {
        Value left = logicalOr.getLeft().accept(this, context);
        Value right = logicalOr.getRight().accept(this, context);

        return left.or(right);
    }

    @Override
    public BooleanValue visit(BooleanLiteral booleanLiteral, Void context) {
        return new BooleanValue(booleanLiteral.getValue());
    }

    @Override
    public DecimalValue visit(DecimalLiteral decimalLiteral, Void context) {
        return new DecimalValue(decimalLiteral.getValue());
    }

    @Override
    public IntegerValue visit(IntegerLiteral integerLiteral, Void context) {
        return new IntegerValue(integerLiteral.getValue());
    }

    @Override
    public StringValue visit(StringLiteral stringLiteral, Void context) {
        return new StringValue(stringLiteral.getValue());
    }
}
