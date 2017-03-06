package org.lemonade.visitors;

import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.value.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public class EvaluateVisitor implements ASTVisitor<Expression> {

    @Override
    public Expression visit(Form form) {

        for (Body body : form.getBodies()) {
            body.accept(this);
        }
        return null;
    }

    @Override
    public Expression visit(Question question) {
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
        return null;
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
        return null;
    }

    @Override
    public Expression visit(NEqBinary nEqBinary) {
        return null;
    }

    @Override
    public Expression visit(GTBinary gtBinary) {
        return null;
    }

    @Override
    public Expression visit(GTEBinary gteBinary) {
        return null;
    }

    @Override
    public Expression visit(LTBinary ltBinary) {
        return null;
    }

    @Override
    public Expression visit(LTEBinary lteBinary) {
        return null;
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
        return identifierValue;
    }

    @Override
    public Expression visit(QLType qlType) {
        return null;
    }

}
