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

public class ExpressionEvaluator implements ExpressionVisitor<Value, ValueTable> {
    @Override
    public Value visitProduct(Product node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.product(right);
    }

    @Override
    public Value visitIncrement(Increment node, ValueTable valueTable) {
        Value value = node.getExpression().accept(this, valueTable);

        return value.increment();
    }

    @Override
    public Value visitSubtraction(Subtraction node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.subtraction(right);
    }

    @Override
    public Value visitDivision(Division node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.division(right);
    }

    @Override
    public Value visitParameter(Parameter node, ValueTable valueTable) {
        return valueTable.lookup(node.getId());
    }

    @Override
    public Value visitGroup(Group node, ValueTable valueTable) {
        return node.getExpression().accept(this, valueTable);
    }

    @Override
    public Value visitAddition(Addition node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.addition(right);
    }

    @Override
    public Value visitDecrement(Decrement node, ValueTable valueTable) {
        Value value = node.getExpression().accept(this, valueTable);

        return value.decrement();
    }

    @Override
    public Value visitGreaterThan(GreaterThan node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.greaterThan(right);
    }

    @Override
    public Value visitNegation(Negation node, ValueTable valueTable) {
        Value value = node.getExpression().accept(this, valueTable);

        return value.negation();
    }

    @Override
    public Value visitNotEqual(NotEqual node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.notEqual(right);
    }

    @Override
    public Value visitAnd(LogicalAnd node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.and(right);
    }

    @Override
    public Value visitLowerThan(LowerThan node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.lowerThan(right);
    }

    @Override
    public Value visitGreaterThanOrEqual(GreaterThanOrEqual node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.greaterThanOrEqual(right);
    }

    @Override
    public Value visitEquals(Equals node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.equal(right);
    }

    @Override
    public Value visitLowerThanOrEqual(LowerThanOrEqual node, ValueTable valueTable) {
        Value left = node.getLeft().accept(this, valueTable);
        Value right = node.getRight().accept(this, valueTable);

        return left.lowerThanOrEqual(right);
    }

    @Override
    public Value visitOr(LogicalOr logicalOr, ValueTable valueTable) {
        Value left = logicalOr.getLeft().accept(this, valueTable);
        Value right = logicalOr.getRight().accept(this, valueTable);

        return left.or(right);
    }

    @Override
    public BooleanValue visitBoolean(BooleanLiteral booleanLiteral, ValueTable valueTable) {
        return new BooleanValue(booleanLiteral.getValue());
    }

    @Override
    public DecimalValue visitDecimal(DecimalLiteral decimalLiteral, ValueTable valueTable) {
        return new DecimalValue(decimalLiteral.getValue());
    }

    @Override
    public IntegerValue visitInteger(IntegerLiteral integerLiteral, ValueTable valueTable) {
        return new IntegerValue(integerLiteral.getValue());
    }

    @Override
    public StringValue visitString(StringLiteral stringLiteral, ValueTable valueTable) {
        return new StringValue(stringLiteral.getValue());
    }

    public Value evaluate(Expression expression, ValueTable valueTable) {
        return expression.accept(this, valueTable);
    }
}
