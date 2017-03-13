package org.lemonade.visitors;

import java.util.HashMap;
import java.util.Map;

import org.lemonade.nodes.ASTNode;
import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Literal;
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
import org.lemonade.nodes.expressions.literal.BooleanLiteral;
import org.lemonade.nodes.expressions.literal.ComparableLiteral;
import org.lemonade.nodes.expressions.literal.DateLiteral;
import org.lemonade.nodes.expressions.literal.DecimalLiteral;
import org.lemonade.nodes.expressions.literal.IdentifierLiteral;
import org.lemonade.nodes.expressions.literal.IntegerLiteral;
import org.lemonade.nodes.expressions.literal.MoneyLiteral;
import org.lemonade.nodes.expressions.literal.NumericLiteral;
import org.lemonade.nodes.expressions.literal.StringLiteral;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.visitors.interfaces.BaseVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;
import org.lemonade.visitors.interfaces.LiteralVisitor;

/**
 *
 */
public class EvaluateVisitor implements ASTVisitor<Expression>, BaseVisitor<Expression>, ExpressionVisitor<Expression>, LiteralVisitor<Expression>{
    Map<String, Literal<?>> environment;

    @Override
    public Expression visit(Form form) {
        environment = new HashMap<>();

        for (Body body : form.getBodies()) {
            body.accept(this);
        }
        return form.accept((BaseVisitor<Expression>) this);
    }

    @Override
    public Expression visit(Question question) {
        IdentifierLiteral identifier = question.getIdentifier();
//        Literal<?> literal = question.getValue();
//
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
    public Expression visit(AndBinary andBinary) {
        BooleanLiteral left = (BooleanLiteral) andBinary.getLeft().accept(this);
        BooleanLiteral right = (BooleanLiteral) andBinary.getRight().accept(this);
        return left.and(right);
    }

    @Override
    public Expression visit(OrBinary orBinary) {
        BooleanLiteral left = (BooleanLiteral) orBinary.getLeft().accept(this);
        BooleanLiteral right = (BooleanLiteral) orBinary.getRight().accept(this);
        return left.or(right);
    }

    @Override
    public Expression visit(PlusBinary plusBinary) {
        NumericLiteral<?> left = (NumericLiteral<?>) plusBinary.getLeft().accept(this);
        NumericLiteral<?> right = (NumericLiteral<?>) plusBinary.getRight().accept(this);
        System.err.println(left.plus(right));
        return left.plus(right);
    }

    @Override
    public Expression visit(ProductBinary productBinary) {
        NumericLiteral<?> left = (NumericLiteral<?>) productBinary.getLeft().accept(this);
        NumericLiteral<?> right = (NumericLiteral<?>) productBinary.getRight().accept(this);
        System.err.println(left.product(right));
        return left.product(right);
    }

    @Override
    public Expression visit(MinusBinary minusBinary) {
        NumericLiteral<?> left = (NumericLiteral<?>) minusBinary.getLeft().accept(this);
        NumericLiteral<?> right = (NumericLiteral<?>) minusBinary.getRight().accept(this);
        System.err.println(left.minus(right));
        return left.minus(right);
    }

    @Override
    public Expression visit(DivideBinary divideBinary) {
        NumericLiteral<?> left = (NumericLiteral<?>) divideBinary.getLeft().accept(this);
        NumericLiteral<?> right = (NumericLiteral<?>) divideBinary.getRight().accept(this);
        System.err.println(left.divide(right));
        return left.divide(right);
    }

    @Override
    public Expression visit(EqBinary eqBinary) {
        Literal<?> left = (Literal<?>) eqBinary.getLeft().accept(this);
        Literal<?> right = (Literal<?>) eqBinary.getRight().accept(this);
        System.err.println(left.equals(right));
        return left.eq(right);
    }

    @Override
    public Expression visit(NEqBinary nEqBinary) {
        Literal<?> left = (Literal<?>) nEqBinary.getLeft().accept(this);
        Literal<?> right = (Literal<?>) nEqBinary.getRight().accept(this);
        System.err.println(left.nEq(right));
        return left.nEq(right);
    }

    @Override
    public Expression visit(GTBinary gtBinary) {
        ComparableLiteral<?> left = (ComparableLiteral<?>) gtBinary.getLeft().accept(this);
        ComparableLiteral<?> right = (ComparableLiteral<?>) gtBinary.getRight().accept(this);
        System.err.println(left.gT(right));
        return left.gT(right);
    }

    @Override
    public Expression visit(GTEBinary gteBinary) {
        ComparableLiteral<?> left = (ComparableLiteral<?>) gteBinary.getLeft().accept(this);
        ComparableLiteral<?> right = (ComparableLiteral<?>) gteBinary.getRight().accept(this);
        System.err.println(left.gTEq(right));
        return left.gTEq(right);
    }

    @Override
    public Expression visit(LTBinary ltBinary) {
        ComparableLiteral<?> left = (ComparableLiteral<?>) ltBinary.getLeft().accept(this);
        ComparableLiteral<?> right = (ComparableLiteral<?>) ltBinary.getRight().accept(this);
        System.err.println(left.lT(right));
        return left.lT(right);
    }

    @Override
    public Expression visit(LTEBinary lteBinary) {
        ComparableLiteral<?> left = (ComparableLiteral<?>) lteBinary.getLeft().accept(this);
        ComparableLiteral<?> right = (ComparableLiteral<?>) lteBinary.getRight().accept(this);
        System.err.println(left.lTEq(right));
        return left.lTEq(right);
    }

    @Override
    public Expression visit(BangUnary bangUnary) {
        BooleanLiteral expression = (BooleanLiteral) bangUnary.getExpression().accept(this);
        return expression.bang();

    }

    @Override
    public Expression visit(NegUnary negUnary) {
        NumericLiteral<?> expression = (NumericLiteral<?>) negUnary.getExpression().accept(this);
        return expression.neg();
    }

    @Override
    public Expression visit(BooleanLiteral booleanValue) {
        return booleanValue;
    }

    @Override
    public Expression visit(DecimalLiteral decimalValue) {
        return decimalValue;
    }

    @Override
    public Expression visit(MoneyLiteral moneyValue) {
        return moneyValue;
    }

    @Override
    public Expression visit(IntegerLiteral integerValue) {
        return integerValue;
    }

    @Override
    public Expression visit(StringLiteral stringValue) {
        return stringValue;
    }

    @Override
    public Expression visit(DateLiteral dateLiteral) {
        return null;
    }

    @Override
    public Expression visit(IdentifierLiteral identifierValue) {
        if (!environment.containsKey(identifierValue.getValue())) {
            throw new RuntimeException("Symbol not found!");
        }
        return environment.get(identifierValue.getValue());
    }

    @Override
    public Expression visit(ASTNode astNode) {
        return null;
    }
}
