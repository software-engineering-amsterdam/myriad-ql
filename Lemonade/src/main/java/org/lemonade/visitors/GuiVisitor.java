package org.lemonade.visitors;

import org.lemonade.nodes.ASTNode;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.expressions.Expression;
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
import org.lemonade.nodes.expressions.literal.DateLiteral;
import org.lemonade.nodes.expressions.literal.DecimalLiteral;
import org.lemonade.nodes.expressions.literal.IdentifierLiteral;
import org.lemonade.nodes.expressions.literal.IntegerLiteral;
import org.lemonade.nodes.expressions.literal.MoneyLiteral;
import org.lemonade.nodes.expressions.literal.StringLiteral;
import org.lemonade.nodes.expressions.unary.BangUnary;
import org.lemonade.nodes.expressions.unary.NegUnary;
import org.lemonade.nodes.types.QLType;

import javafx.scene.layout.Pane;

public class GuiVisitor implements ASTVisitor<Expression> {

    private Pane pane;

    public GuiVisitor(Pane pane) {
        this.pane = pane;
    }

    @Override public Expression visit(final Form form) {
        return null;
    }

    @Override public Expression visit(final Question question) {
        return null;
    }

    @Override public Expression visit(final Conditional conditional) {
        return null;
    }

    @Override public Expression visit(final Expression expression) {
        return null;
    }

    @Override public Expression visit(final AndBinary andBinary) {
        return null;
    }

    @Override public Expression visit(final OrBinary orBinary) {
        return null;
    }

    @Override public Expression visit(final PlusBinary plusBinary) {
        return null;
    }

    @Override public Expression visit(final ProductBinary productBinary) {
        return null;
    }

    @Override public Expression visit(final MinusBinary minusBinary) {
        return null;
    }

    @Override public Expression visit(final DivideBinary divideBinary) {
        return null;
    }

    @Override public Expression visit(final EqBinary eqBinary) {
        return null;
    }

    @Override public Expression visit(final NEqBinary nEqBinary) {
        return null;
    }

    @Override public Expression visit(final GTBinary gtBinary) {
        return null;
    }

    @Override public Expression visit(final GTEBinary gteBinary) {
        return null;
    }

    @Override public Expression visit(final LTBinary ltBinary) {
        return null;
    }

    @Override public Expression visit(final LTEBinary lteBinary) {
        return null;
    }

    @Override public Expression visit(final BangUnary bangUnary) {
        return null;
    }

    @Override public Expression visit(final NegUnary negUnary) {
        return null;
    }

    @Override public Expression visit(final BooleanLiteral booleanValue) {
        return null;
    }

    @Override public Expression visit(final DecimalLiteral decimalValue) {
        return null;
    }

    @Override public Expression visit(final MoneyLiteral moneyValue) {
        return null;
    }

    @Override public Expression visit(final IntegerLiteral integerValue) {
        return null;
    }

    @Override public Expression visit(final StringLiteral stringValue) {
        return null;
    }

    @Override public Expression visit(final IdentifierLiteral identifierValue) {
        return null;
    }

    @Override public Expression visit(final QLType qlType) {
        return null;
    }

    @Override public Expression visit(final ASTNode astNode) {
        return null;
    }

    @Override public Expression visit(final DateLiteral dateLiteral) {
        return null;
    }
}
