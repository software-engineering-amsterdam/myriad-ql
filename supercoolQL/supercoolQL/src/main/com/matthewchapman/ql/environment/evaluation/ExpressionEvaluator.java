package com.matthewchapman.ql.environment.evaluation;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.expression.Parameter;
import com.matthewchapman.ql.ast.expression.ParameterGroup;
import com.matthewchapman.ql.ast.expression.binary.*;
import com.matthewchapman.ql.ast.expression.literal.BooleanLiteral;
import com.matthewchapman.ql.ast.expression.literal.IntegerLiteral;
import com.matthewchapman.ql.ast.expression.literal.StringLiteral;
import com.matthewchapman.ql.ast.expression.unary.Negation;
import com.matthewchapman.ql.environment.datastores.ValueTable;
import com.matthewchapman.ql.environment.values.BooleanValue;
import com.matthewchapman.ql.environment.values.IntegerValue;
import com.matthewchapman.ql.environment.values.StringValue;
import com.matthewchapman.ql.environment.values.Value;
import com.matthewchapman.ql.visitors.ExpressionVisitor;

/**
 * Created by matt on 20/03/2017.
 *
 * Uses the visitor pattern to evaluate given expressions
 */
public class ExpressionEvaluator implements ExpressionVisitor<Value, String> {

    private ValueTable valueTable;

    public Value evaluateExpression(String id, Expression expression, ValueTable valueTable) {
        this.valueTable = valueTable;
        return expression.accept(this, id);
    }

    @Override
    public Value visit(Addition addition, String context) {
        Value left = addition.getLeft().accept(this, context);
        Value right = addition.getRight().accept(this, context);
        return left.add(right);
    }

    @Override
    public Value visit(Division division, String context) {
        Value left = division.getLeft().accept(this, context);
        Value right = division.getRight().accept(this, context);
        return left.divide(right);
    }

    @Override
    public Value visit(Equal equal, String context) {
        Value left = equal.getLeft().accept(this, context);
        Value right = equal.getRight().accept(this, context);
        return left.equalTo(right);
    }

    @Override
    public Value visit(GreaterThan greaterThan, String context) {
        Value left = greaterThan.getLeft().accept(this, context);
        Value right = greaterThan.getRight().accept(this, context);
        return left.greaterThan(right);
    }

    @Override
    public Value visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        Value left = greaterThanEqualTo.getLeft().accept(this, context);
        Value right = greaterThanEqualTo.getRight().accept(this, context);
        return left.greaterThanEqualTo(right);
    }

    @Override
    public Value visit(LessThan lessThan, String context) {
        Value left = lessThan.getLeft().accept(this, context);
        Value right = lessThan.getRight().accept(this, context);
        return left.lessThan(right);
    }

    @Override
    public Value visit(LessThanEqualTo lessThanEqualTo, String context) {
        Value left = lessThanEqualTo.getLeft().accept(this, context);
        Value right = lessThanEqualTo.getRight().accept(this, context);
        return left.lessThanEqualTo(right);
    }

    @Override
    public Value visit(LogicalAnd logicalAnd, String context) {
        Value left = logicalAnd.getLeft().accept(this, context);
        Value right = logicalAnd.getRight().accept(this, context);
        return left.logicalAnd(right);
    }

    @Override
    public Value visit(LogicalOr logicalOr, String context) {
        Value left = logicalOr.getLeft().accept(this, context);
        Value right = logicalOr.getRight().accept(this, context);
        return left.logicalOr(right);
    }

    @Override
    public Value visit(Multiplication multiplication, String context) {
        Value left = multiplication.getLeft().accept(this, context);
        Value right = multiplication.getRight().accept(this, context);
        return left.multiply(right);
    }

    @Override
    public Value visit(NotEqual notEqual, String context) {
        Value left = notEqual.getLeft().accept(this, context);
        Value right = notEqual.getRight().accept(this, context);
        return left.notEqualTo(right);
    }

    @Override
    public Value visit(Subtraction subtraction, String context) {
        Value left = subtraction.getLeft().accept(this, context);
        Value right = subtraction.getRight().accept(this, context);
        return left.subtract(right);
    }

    @Override
    public Value visit(Negation negation, String context) {
        Value value = negation.getExpression().accept(this, context);
        return value.negate(value);
    }

    @Override
    public Value visit(Parameter parameter, String context) {
        return valueTable.getValueByID(parameter.getID());
    }

    @Override
    public Value visit(ParameterGroup parameterGroup, String context) {
        return parameterGroup.getExpression().accept(this, context);
    }

    @Override
    public Value visit(StringLiteral stringLiteral, String context) {
        return new StringValue(stringLiteral.getValue());
    }

    @Override
    public Value visit(IntegerLiteral integerLiteral, String context) {
        return new IntegerValue(integerLiteral.getValue());
    }

    @Override
    public Value visit(BooleanLiteral booleanLiteral, String context) {
        return new BooleanValue(booleanLiteral.getValue());
    }
}
