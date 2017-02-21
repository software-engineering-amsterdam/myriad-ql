package org.lemonade.visitors;

import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
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
public class EvaluateVisitor implements ASTVisitor<Literal> {
    @Override
    public Literal visit(Form form) {
        return null;
    }

    @Override
    public Literal visit(Question question) {
        return null;
    }

    @Override
    public Literal visit(Conditional conditional) {
        return null;
    }

    @Override
    public Literal visit(Expression expression) {
        return null;
    }

    @Override
    public Literal visit(AndBinary andBinary) {
        return null;
    }

    @Override
    public Literal visit(OrBinary orBinary) {
        return null;
    }

    @Override
    public Literal visit(PlusBinary plusBinary) {
        return null;
    }

    @Override
    public Literal visit(ProductBinary productBinary) {
        return null;
    }

    @Override
    public Literal visit(MinusBinary minusBinary) {
        return null;
    }

    @Override
    public Literal visit(DivideBinary divideBinary) {
        return null;
    }

    @Override
    public Literal visit(EqBinary eqBinary) {
        return null;
    }

    @Override
    public Literal visit(NEqBinary nEqBinary) {
        return null;
    }

    @Override
    public Literal visit(GTBinary gtBinary) {
        return null;
    }

    @Override
    public Literal visit(GTEBinary gteBinary) {
        return null;
    }

    @Override
    public Literal visit(LTBinary ltBinary) {
        return null;
    }

    @Override
    public Literal visit(LTEBinary lteBinary) {
        return null;
    }

    @Override
    public Literal visit(BangUnary bangUnary) {
        return null;
    }

    @Override
    public Literal visit(NegUnary negUnary) {
        return null;
    }

    @Override
    public Literal visit(BooleanLit booleanLit) {
        return null;
    }

    @Override
    public Literal visit(DecimalLit decimalLit) {
        return null;
    }

    @Override
    public Literal visit(MoneyLit moneyLit) {
        return null;
    }

    @Override
    public Literal visit(IntegerLit integerLit) {
        return null;
    }

    @Override
    public Literal visit(StringLit stringLit) {
        return null;
    }

    @Override
    public Literal visit(IdentifierLit identifierLit) {
        return null;
    }

    @Override
    public Literal visit(QLType qlType) {
        return null;
    }
}
