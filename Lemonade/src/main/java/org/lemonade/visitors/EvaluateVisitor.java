package org.lemonade.visitors;

import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.BinaryExpression;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Literal;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
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
        return null;
    }

    @Override
    public Expression visit(OrBinary orBinary) {
        return null;
    }

    @Override
    public Expression visit(PlusBinary plusBinary) {
        IntegerLit left = (IntegerLit) plusBinary.getLeft().accept(this);
        IntegerLit right = (IntegerLit) plusBinary.getLeft().accept(this);
        System.err.println(left.plus(right));
        return left.plus(right);
    }

    @Override
    public Expression visit(ProductBinary productBinary) {
        return null;
    }

    @Override
    public Expression visit(MinusBinary minusBinary) {
        return null;
    }

    @Override
    public Expression visit(DivideBinary divideBinary) {
        return null;
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
        BooleanLit expression = (BooleanLit) bangUnary.getExpression().accept(this);
        return expression.neg();
    }

    @Override
    public Expression visit(NegUnary negUnary) {
        return null;
    }

    @Override
    public Expression visit(BooleanLit booleanLit) {
        return booleanLit;
    }

    @Override
    public Expression visit(DecimalLit decimalLit) {
        return decimalLit;
    }

    @Override
    public Expression visit(MoneyLit moneyLit) {
        return moneyLit;
    }

    @Override
    public Expression visit(IntegerLit integerLit) {
        return integerLit;
    }

    @Override
    public Expression visit(StringLit stringLit) {
        return stringLit;
    }

    @Override
    public Expression visit(IdentifierLit identifierLit) {
        return identifierLit;
    }

    @Override
    public Expression visit(QLType qlType) {
        return null;
    }

}
