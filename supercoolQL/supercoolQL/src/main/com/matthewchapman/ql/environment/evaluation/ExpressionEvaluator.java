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
import com.matthewchapman.ql.environment.values.IntegerValue;
import com.matthewchapman.ql.environment.values.Value;
import com.matthewchapman.ql.validation.visitors.ExpressionVisitor;

/**
 * Created by matt on 20/03/2017.
 */
public class ExpressionEvaluator implements ExpressionVisitor<Value, String> {

    public Value evaluateExpression(String id, Expression expression, ValueTable valueTable) {
        valueTable.addValue(id, expression.accept(this, id));
        Value value = expression.accept(this, id);
        return null;
    }

    @Override
    public Value visit(Addition addition, String context) {
        Value left = addition.getLeft().accept(this, context);
        Value right = addition.getRight().accept(this, context);

        return left.add(right);
    }

    @Override
    public Value visit(Division division, String context) {
        return null;
    }

    @Override
    public Value visit(Equal equal, String context) {
        return null;
    }

    @Override
    public Value visit(GreaterThan greaterThan, String context) {
        return null;
    }

    @Override
    public Value visit(GreaterThanEqualTo greaterThanEqualTo, String context) {
        return null;
    }

    @Override
    public Value visit(LessThan lessThan, String context) {
        return null;
    }

    @Override
    public Value visit(LessThanEqualTo lessThanEqualTo, String context) {
        return null;
    }

    @Override
    public Value visit(LogicalAnd logicalAnd, String context) {
        return null;
    }

    @Override
    public Value visit(LogicalOr logicalOr, String context) {
        return null;
    }

    @Override
    public Value visit(Multiplication multiplication, String context) {
        return null;
    }

    @Override
    public Value visit(NotEqual notEqual, String context) {
        return null;
    }

    @Override
    public Value visit(Subtraction subtraction, String context) {
        return null;
    }

    @Override
    public Value visit(Negation negation, String context) {
        return null;
    }

    @Override
    public Value visit(Parameter parameter, String context) {
        return null;
    }

    @Override
    public Value visit(ParameterGroup parameterGroup, String context) {
        return null;
    }

    @Override
    public Value visit(StringLiteral stringLiteral, String context) {
        return null;
    }

    @Override
    public Value visit(IntegerLiteral integerLiteral, String context) {
        return new IntegerValue(integerLiteral.getValue());
    }

    @Override
    public Value visit(BooleanLiteral booleanLiteral, String context) {
        return null;
    }
}
