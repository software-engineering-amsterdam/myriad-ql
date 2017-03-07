package org.lemonade.visitors;

import org.lemonade.gui.elements.GuiBooleanValue;
import org.lemonade.gui.elements.GuiDateValue;
import org.lemonade.gui.elements.GuiDecimalValue;
import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.GuiIdentifierValue;
import org.lemonade.gui.elements.GuiIntegerValue;
import org.lemonade.gui.elements.GuiMoneyValue;
import org.lemonade.gui.elements.GuiQuestion;
import org.lemonade.gui.elements.GuiStringValue;
import org.lemonade.gui.elements.GuiValue;
import org.lemonade.nodes.ASTNode;
import org.lemonade.nodes.Body;
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

public class GuiVisitor implements ASTVisitor<GuiElement> {

    private Pane pane;

    public GuiVisitor(Pane pane) {
        this.pane = pane;
    }

    @Override public GuiElement visit(final Form form) {
        for (Body body : form.getBodies()) {
            body.accept(this);
        }
        return form.accept(this);
    }

    @Override public GuiElement visit(final Question question) {
        question.getType().accept(this);
        return null;
    }

    @Override public GuiElement visit(final Conditional conditional) {
        return null;
    }

    @Override public GuiElement visit(final Expression expression) {
        return null;
    }

    @Override public GuiElement visit(final AndBinary andBinary) {
        return null;
    }

    @Override public GuiElement visit(final OrBinary orBinary) {
        return null;
    }

    @Override public GuiElement visit(final PlusBinary plusBinary) {
        return null;
    }

    @Override public GuiElement visit(final ProductBinary productBinary) {
        return null;
    }

    @Override public GuiElement visit(final MinusBinary minusBinary) {
        return null;
    }

    @Override public GuiElement visit(final DivideBinary divideBinary) {
        return null;
    }

    @Override public GuiElement visit(final EqBinary eqBinary) {
        return null;
    }

    @Override public GuiElement visit(final NEqBinary nEqBinary) {
        return null;
    }

    @Override public GuiElement visit(final GTBinary gtBinary) {
        return null;
    }

    @Override public GuiElement visit(final GTEBinary gteBinary) {
        return null;
    }

    @Override public GuiElement visit(final LTBinary ltBinary) {
        return null;
    }

    @Override public GuiElement visit(final LTEBinary lteBinary) {
        return null;
    }

    @Override public GuiElement visit(final BangUnary bangUnary) {
        return null;
    }

    @Override public GuiElement visit(final NegUnary negUnary) {
        return null;
    }

    @Override public GuiElement visit(final BooleanLiteral booleanValue) {
        final GuiBooleanValue value = new GuiBooleanValue();
        return value;
    }

    @Override public GuiElement visit(final DecimalLiteral decimalValue) {
        final GuiDecimalValue value = new GuiDecimalValue();
        return value;
    }

    @Override public GuiElement visit(final DateLiteral dateLiteral) {
        final GuiDateValue value = new GuiDateValue();
        return value;
    }

    @Override public GuiElement visit(final MoneyLiteral moneyValue) {
        final GuiMoneyValue value = new GuiMoneyValue();
        return value;
    }

    @Override public GuiElement visit(final IntegerLiteral integerValue) {
        final GuiIntegerValue value = new GuiIntegerValue();
        return value;
    }

    @Override public GuiElement visit(final StringLiteral stringValue) {
        final GuiStringValue value = new GuiStringValue();
        return value;
    }

    @Override public GuiElement visit(final IdentifierLiteral identifierValue) {
        final GuiIdentifierValue value = new GuiIdentifierValue(identifierValue.getValue());
        return value;
    }

    @Override public GuiElement visit(final QLType qlType) {
        return null;
    }

    @Override public GuiElement visit(final ASTNode astNode) {
        return null;
    }
}
