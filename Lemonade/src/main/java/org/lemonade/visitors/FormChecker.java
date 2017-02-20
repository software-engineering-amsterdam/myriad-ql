package org.lemonade.visitors;

import org.lemonade.nodes.ASTNode;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.Type;
import org.lemonade.nodes.expressions.binary.*;
import org.lemonade.nodes.expressions.literal.*;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;

/**
 *
 */
public class FormChecker implements ASTVisitor<Boolean> {

    public Boolean visit(Form form) {
        System.err.println("Form identifier: " + form.getIdentifier());
        return true;
    }

    @Override
    public Boolean visit(Question question) {
        return null;
    }

    @Override
    public Boolean visit(Conditional conditional) {
        return null;
    }

    @Override
    public Boolean visit(Expression expression) {
        return null;
    }

    @Override
    public Boolean visit(AndBinary andBinary) {
        return null;
    }

    @Override
    public Boolean visit(OrBinary orBinary) {
        return null;
    }

    @Override
    public Boolean visit(PlusBinary plusBinary) {
        return null;
    }

    @Override
    public Boolean visit(ProductBinary productBinary) {
        return null;
    }

    @Override
    public Boolean visit(MinusBinary minusBinary) {
        return null;
    }

    @Override
    public Boolean visit(DivideBinary divideBinary) {
        return null;
    }

    @Override
    public Boolean visit(EqBinary eqBinary) {
        return null;
    }

    @Override
    public Boolean visit(NEqBinary nEqBinary) {
        return null;
    }

    @Override
    public Boolean visit(GTBinary gtBinary) {
        return null;
    }

    @Override
    public Boolean visit(GTEBinary gteBinary) {
        return null;
    }

    @Override
    public Boolean visit(LTBinary ltBinary) {
        return null;
    }

    @Override
    public Boolean visit(LTEBinary lteBinary) {
        return null;
    }

    @Override
    public Boolean visit(BangUnary bangUnary) {
        return null;
    }

    @Override
    public Boolean visit(NegUnary negUnary) {
        return null;
    }

    @Override
    public Boolean visit(BooleanLit booleanLit) {
        return null;
    }

    @Override
    public Boolean visit(DecimalLit decimalLit) {
        return null;
    }

    @Override
    public Boolean visit(IntegerLit integerLit) {
        return null;
    }

    @Override
    public Boolean visit(StringLit stringLit) {
        return null;
    }

    @Override
    public Boolean visit(IdentifierLit identifierLit) {
        return null;
    }

    @Override
    public Boolean visit(Type type) {
        return null;
    }
}
