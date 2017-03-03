package org.ql.evaluator.value;

import org.ql.ast.expression.ExpressionVisitor;
import org.ql.ast.expression.Parameter;
import org.ql.ast.expression.arithmetic.*;
import org.ql.ast.expression.literal.BooleanLiteral;
import org.ql.ast.expression.literal.DecimalLiteral;
import org.ql.ast.expression.literal.IntegerLiteral;
import org.ql.ast.expression.literal.StringLiteral;
import org.ql.ast.expression.relational.*;

public class Evaluator implements ExpressionVisitor<Value, Void> {
    @Override
    public Value visit(Negation node, Void context) {
        return new BooleanValue();
    }

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
    public Value visit(NotEqual node, Void context) {
        return null;
    }

    @Override
    public Value visit(LogicalAnd node, Void context) {
        return null;
    }

    @Override
    public Value visit(LowerThan node, Void context) {
        return null;
    }

    @Override
    public Value visit(GreaterThanOrEqual node, Void context) {
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
    public Value visit(GreaterThan node, Void context) {
        return null;
    }

    @Override
    public Value visit(Decrement node, Void context) {
        return null;
    }

    @Override
    public Value visit(Equals node, Void context) {
        return null;
    }

    @Override
    public Value visit(LowerThanOrEqual node, Void context) {
        return null;
    }

    @Override
    public Value visit(LogicalOr node, Void context) {
        return null;
    }

    @Override
    public Value visit(BooleanLiteral node, Void context) {
        return null;
    }

    @Override
    public Value visit(DecimalLiteral node, Void context) {
        return null;
    }

    @Override
    public Value visit(IntegerLiteral node, Void context) {
        return null;
    }

    @Override
    public Value visit(StringLiteral node, Void context) {
        return null;
    }
}
