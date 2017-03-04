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
        return null;
    }

    @Override
    public Value visit(Increment node, Void context) {
        return null;
    }

    @Override
    public Value visit(Subtraction node, Void context) {
        return null;
    }

    @Override
    public Value visit(Division node, Void context) {
        return null;
    }

    @Override
    public Value visit(Parameter node, Void context) {
        return null;
    }

    @Override
    public Value visit(Group node, Void context) {
        return null;
    }

    @Override
    public Value visit(Addition node, Void context) {
        return null;
    }

    @Override
    public Value visit(Decrement node, Void context) {
        return null;
    }

    @Override
    public BooleanValue visit(GreaterThan node, Void context) {
        return null;
    }

    @Override
    public BooleanValue visit(Negation node, Void context) {
        return null;
    }

    @Override
    public BooleanValue visit(NotEqual node, Void context) {
        return null;
    }

    @Override
    public BooleanValue visit(LogicalAnd node, Void context) {
        return null;
    }

    @Override
    public BooleanValue visit(LowerThan node, Void context) {
        return null;
    }

    @Override
    public BooleanValue visit(GreaterThanOrEqual node, Void context) {
        return null;
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
        Value left = logicalOr.getLeft().accept(this, null);
        Value right = logicalOr.getRight().accept(this, null);

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
