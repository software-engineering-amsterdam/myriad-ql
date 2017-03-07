package org.lemonade.visitors;

import java.util.HashMap;
import java.util.Map;

import org.lemonade.nodes.ASTNode;
import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Value;
import org.lemonade.nodes.expressions.binary.AndBinary;
import org.lemonade.nodes.expressions.binary.DivideBinary;
import org.lemonade.nodes.expressions.binary.EqBinary;
import org.lemonade.nodes.expressions.binary.GTBinary;
import org.lemonade.nodes.expressions.binary.GTEBinary;
import org.lemonade.nodes.expressions.binary.LTBinary;
import org.lemonade.nodes.expressions.binary.LTEBinary;
import org.lemonade.nodes.expressions.binary.MinusBinary;
import org.lemonade.nodes.expressions.binary.NEqBinary;
import org.lemonade.nodes.expressions.binary.OrBinary;
import org.lemonade.nodes.expressions.binary.PlusBinary;
import org.lemonade.nodes.expressions.binary.ProductBinary;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.nodes.expressions.value.BooleanValue;
import org.lemonade.nodes.expressions.value.ComparableValue;
import org.lemonade.nodes.expressions.value.DecimalValue;
import org.lemonade.nodes.expressions.value.IdentifierValue;
import org.lemonade.nodes.expressions.value.IntegerValue;
import org.lemonade.nodes.expressions.value.MoneyValue;
import org.lemonade.nodes.expressions.value.NumericValue;
import org.lemonade.nodes.expressions.value.StringValue;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public class EvaluateVisitor implements ASTVisitor<Expression> {
    Map<String, Value<?>> environment;

    @Override
    public Expression visit(Form form) {
        environment = new HashMap<>();

        for (Body body : form.getBodies()) {
            body.accept(this);
        }
        return form.accept(this);
    }

    @Override
    public Expression visit(Question question) {
        String identifier = question.getIdentifier();
        //        Value<?> value = question.getValue();

        //        assert !environment.containsKey(identifier);
        //        environment.put(identifier, new UndefinedValue(question.getType()));
        return null;
    }

    @Override
    public Expression visit(Conditional conditional) {
        conditional.getCondition().accept(this);
        return null;
    }

    @Override
    public Expression visit(Expression expression) {
        return null;
    }

    @Override
    public Expression visit(AndBinary andBinary) {
        BooleanValue left = (BooleanValue) andBinary.getLeft().accept(this);
        BooleanValue right = (BooleanValue) andBinary.getRight().accept(this);
        return left.and(right);
    }

    @Override
    public Expression visit(OrBinary orBinary) {
        BooleanValue left = (BooleanValue) orBinary.getLeft().accept(this);
        BooleanValue right = (BooleanValue) orBinary.getRight().accept(this);
        return left.or(right);
    }

    @Override
    public Expression visit(PlusBinary plusBinary) {
        NumericValue<?> left = (NumericValue<?>) plusBinary.getLeft().accept(this);
        NumericValue<?> right = (NumericValue<?>) plusBinary.getRight().accept(this);
        System.err.println(left.plus(right));
        return left.plus(right);
    }

    @Override
    public Expression visit(ProductBinary productBinary) {
        NumericValue<?> left = (NumericValue<?>) productBinary.getLeft().accept(this);
        NumericValue<?> right = (NumericValue<?>) productBinary.getRight().accept(this);
        System.err.println(left.product(right));
        return left.product(right);
    }

    @Override
    public Expression visit(MinusBinary minusBinary) {
        NumericValue<?> left = (NumericValue<?>) minusBinary.getLeft().accept(this);
        NumericValue<?> right = (NumericValue<?>) minusBinary.getRight().accept(this);
        System.err.println(left.minus(right));
        return left.minus(right);
    }

    @Override
    public Expression visit(DivideBinary divideBinary) {
        NumericValue<?> left = (NumericValue<?>) divideBinary.getLeft().accept(this);
        NumericValue<?> right = (NumericValue<?>) divideBinary.getRight().accept(this);
        System.err.println(left.divide(right));
        return left.divide(right);
    }

    @Override
    public Expression visit(EqBinary eqBinary) {
        Value<?> left = (Value<?>) eqBinary.getLeft().accept(this);
        Value<?> right = (Value<?>) eqBinary.getRight().accept(this);
        System.err.println(left.equals(right));
        return left.eq(right);
    }

    @Override
    public Expression visit(NEqBinary nEqBinary) {
        Value<?> left = (Value<?>) nEqBinary.getLeft().accept(this);
        Value<?> right = (Value<?>) nEqBinary.getRight().accept(this);
        System.err.println(left.nEq(right));
        return left.nEq(right);
    }

    @Override
    public Expression visit(GTBinary gtBinary) {
        ComparableValue<?> left = (ComparableValue<?>) gtBinary.getLeft().accept(this);
        ComparableValue<?> right = (ComparableValue<?>) gtBinary.getRight().accept(this);
        System.err.println(left.gT(right));
        return left.gT(right);
    }

    @Override
    public Expression visit(GTEBinary gteBinary) {
        ComparableValue<?> left = (ComparableValue<?>) gteBinary.getLeft().accept(this);
        ComparableValue<?> right = (ComparableValue<?>) gteBinary.getRight().accept(this);
        System.err.println(left.gTEq(right));
        return left.gTEq(right);
    }

    @Override
    public Expression visit(LTBinary ltBinary) {
        ComparableValue<?> left = (ComparableValue<?>) ltBinary.getLeft().accept(this);
        ComparableValue<?> right = (ComparableValue<?>) ltBinary.getRight().accept(this);
        System.err.println(left.lT(right));
        return left.lT(right);
    }

    @Override
    public Expression visit(LTEBinary lteBinary) {
        ComparableValue<?> left = (ComparableValue<?>) lteBinary.getLeft().accept(this);
        ComparableValue<?> right = (ComparableValue<?>) lteBinary.getRight().accept(this);
        System.err.println(left.lTEq(right));
        return left.lTEq(right);
    }

    @Override
    public Expression visit(BangUnary bangUnary) {
        BooleanValue expression = (BooleanValue) bangUnary.getExpression().accept(this);
        return expression.bang();

    }

    @Override
    public Expression visit(NegUnary negUnary) {
        NumericValue<?> expression = (NumericValue<?>) negUnary.getExpression().accept(this);
        return expression.neg();
    }

    @Override
    public Expression visit(BooleanValue booleanValue) {
        return booleanValue;
    }

    @Override
    public Expression visit(DecimalValue decimalValue) {
        return decimalValue;
    }

    @Override
    public Expression visit(MoneyValue moneyValue) {
        return moneyValue;
    }

    @Override
    public Expression visit(IntegerValue integerValue) {
        return integerValue;
    }

    @Override
    public Expression visit(StringValue stringValue) {
        return stringValue;
    }

    @Override
    public Expression visit(IdentifierValue identifierValue) {
        if (!environment.containsKey(identifierValue.getValue())) {
            throw new RuntimeException("Symbol not found!");
        }
        return environment.get(identifierValue.getValue());
    }

    @Override
    public Expression visit(QLType qlType) {
        return null;
    }

    @Override
    public Expression visit(ASTNode astNode) {
        return astNode.accept(this);
    }

}
