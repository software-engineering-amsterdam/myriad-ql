package org.lemonade.visitors;

import org.lemonade.nodes.Position;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.visitors.interfaces.ExpressionVisitor;

/**
 *
 */
public class CycleDetector implements ExpressionVisitor<Boolean> {

    private String identifier;
    private String error;
    private Position position;

    public CycleDetector(String identifier, Position parentPosition) {
        this.identifier = identifier;
        this.position = parentPosition;
    }

    public String getError() {
        return error;
    }

    @Override
    public Boolean visit(AndBinary andBinary) {
        boolean left = andBinary.getLeft().accept(this);
        boolean right = andBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(OrBinary orBinary) {
        boolean left = orBinary.getLeft().accept(this);
        boolean right = orBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(PlusBinary plusBinary) {
        boolean left = plusBinary.getLeft().accept(this);
        boolean right = plusBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(ProductBinary productBinary) {
        boolean left = productBinary.getLeft().accept(this);
        boolean right = productBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(MinusBinary minusBinary) {
        boolean left = minusBinary.getLeft().accept(this);
        boolean right = minusBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(DivideBinary divideBinary) {
        boolean left = divideBinary.getLeft().accept(this);
        boolean right = divideBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(EqBinary eqBinary) {
        boolean left = eqBinary.getLeft().accept(this);
        boolean right = eqBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(NEqBinary nEqBinary) {
        boolean left = nEqBinary.getLeft().accept(this);
        boolean right = nEqBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(GTBinary gtBinary) {
        boolean left = gtBinary.getLeft().accept(this);
        boolean right = gtBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(GTEBinary gteBinary) {
        boolean left = gteBinary.getLeft().accept(this);
        boolean right = gteBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(LTBinary ltBinary) {
        boolean left = ltBinary.getLeft().accept(this);
        boolean right = ltBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(LTEBinary lteBinary) {
        boolean left = lteBinary.getLeft().accept(this);
        boolean right = lteBinary.getRight().accept(this);
        return left || right;
    }

    @Override
    public Boolean visit(BangUnary bangUnary) {
        boolean expression = bangUnary.getExpression().accept(this);
        return expression;
    }

    @Override
    public Boolean visit(NegUnary negUnary) {
        boolean expression = negUnary.getExpression().accept(this);
        return expression;
    }

    @Override
    public Boolean visit(BooleanLiteral booleanValue) {
        return false;
    }

    @Override
    public Boolean visit(DecimalLiteral decimalValue) {
        return false;
    }

    @Override
    public Boolean visit(DateLiteral dateValue) {
        return false;
    }

    @Override
    public Boolean visit(MoneyLiteral moneyValue) {
        return false;
    }

    @Override
    public Boolean visit(IntegerLiteral integerValue) {
        return false;
    }

    @Override
    public Boolean visit(StringLiteral stringValue) {
        return false;
    }

    @Override
    public Boolean visit(IdentifierLiteral identifierValue) {
        boolean cyclic = identifierValue.getValue().equals(this.identifier);
        if (cyclic)
            error = "Cyclic dependency on " + identifier +  " found at " + this.position;
        return cyclic;
    }
}
