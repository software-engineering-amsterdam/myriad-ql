package org.lemonade.visitors;

import java.util.ArrayList;
import java.util.List;

import org.lemonade.gui.GuiBody;
import org.lemonade.gui.GuiConditional;
import org.lemonade.gui.GuiExpression;
import org.lemonade.gui.GuiForm;
import org.lemonade.gui.GuiQuestion;
import org.lemonade.gui.elements.GuiBooleanElement;
import org.lemonade.gui.elements.GuiDateElement;
import org.lemonade.gui.elements.GuiDecimalElement;
import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.GuiIntegerElement;
import org.lemonade.gui.elements.GuiLabelElement;
import org.lemonade.gui.elements.GuiMoneyElement;
import org.lemonade.gui.elements.GuiStringElement;
import org.lemonade.gui.expressions.binary.GuiAndBinary;
import org.lemonade.gui.expressions.binary.GuiDivideBinary;
import org.lemonade.gui.expressions.binary.GuiMinusBinary;
import org.lemonade.gui.expressions.binary.GuiOrBinary;
import org.lemonade.gui.expressions.binary.GuiPlusBinary;
import org.lemonade.gui.expressions.binary.GuiProductBinary;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
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
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLDateType;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.visitors.interfaces.BaseVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;
import org.lemonade.visitors.interfaces.TypeVisitor;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class GuiVisitor implements BaseVisitor<GuiBody>, TypeVisitor<GuiElement>, ExpressionVisitor<GuiExpression> {

    private GridPane pane;
    private int rowCount;

    public GuiVisitor(GridPane pane) {
        this.pane = pane;
        this.rowCount = 0;
    }

    @Override
    public GuiBody visit(final Form form) {
        List<GuiBody> bodies = new ArrayList<>();
        GuiIdentifierValue identifier = new GuiIdentifierValue(form.getIdentifier().getValue());

        for (Body body : form.getBodies()) {
            bodies.add((GuiBody) body.accept(this));
            rowCount++;
        }
        return new GuiForm(identifier, bodies);
    }

    @Override
    public GuiBody visit(final Question question) {
        GuiIdentifierValue identifier = new GuiIdentifierValue(question.getIdentifier().getValue());
        GuiElement element = question.getType().accept(this);
        GuiLabelElement labelElement = new GuiLabelElement(question.getLabel());
        GuiQuestion guiQuestion = new GuiQuestion(identifier, labelElement, element);

        final GridPane gridPane = new GridPane();
        gridPane.setHgap(6);
        gridPane.setVgap(6);
        gridPane.setMaxWidth(560);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);

        GridPane.setConstraints(labelElement.getWidget(), 0, 0);
        GridPane.setConstraints(element.getWidget(), 1, 0);
        gridPane.getChildren().addAll(labelElement.getWidget(), element.getWidget());

        gridPane.managedProperty().bind(gridPane.visibleProperty());

        pane.addRow(rowCount, gridPane);

        return guiQuestion;
    }

    // TODO: figure out how to store related identifiers
    @Override
    public GuiBody visit(final Conditional conditional) {
        List<GuiBody> conditionalBodies = new ArrayList<>();
        for (Body body : conditional.getBodies()) {
            conditionalBodies.add(body.accept(this));
        }
        return new GuiConditional(conditionalBodies);
    }

    @Override
    public GuiElement visit(final QLIntegerType qlIntegerType) {
        return new GuiIntegerElement();
    }

    @Override
    public GuiElement visit(final QLBooleanType qlBooleanType) {
        return new GuiBooleanElement();
    }

    @Override
    public GuiElement visit(final QLDateType qlDateType) {
        return new GuiDateElement();
    }

    @Override
    public GuiElement visit(final QLDecimalType qlDecimalType) {
        return new GuiDecimalElement();
    }

    @Override
    public GuiElement visit(final QLMoneyType qlMoneyType) {
        return new GuiMoneyElement();
    }

    @Override
    public GuiElement visit(final QLStringType qlStringType) {
        return new GuiStringElement();
    }

    @Override
    public GuiExpression visit(final AndBinary andBinary) {
        GuiExpression left = andBinary.getLeft().accept(this);
        GuiExpression right = andBinary.getRight().accept(this);
        return new GuiAndBinary(left, right);
    }

    @Override
    public GuiExpression visit(final OrBinary orBinary) {
        GuiExpression left = orBinary.getLeft().accept(this);
        GuiExpression right = orBinary.getRight().accept(this);
        return new GuiOrBinary(left, right);
    }

    @Override
    public GuiExpression visit(final PlusBinary plusBinary) {
        GuiExpression left = plusBinary.getLeft().accept(this);
        GuiExpression right = plusBinary.getRight().accept(this);
        return new GuiPlusBinary(left, right);
    }

    @Override
    public GuiExpression visit(final ProductBinary productBinary) {
        GuiExpression left = productBinary.getLeft().accept(this);
        GuiExpression right = productBinary.getRight().accept(this);
        return new GuiProductBinary(left, right);
    }

    @Override
    public GuiExpression visit(final MinusBinary minusBinary) {
        GuiExpression left = minusBinary.getLeft().accept(this);
        GuiExpression right = minusBinary.getRight().accept(this);
        return new GuiMinusBinary(left, right);
    }

    @Override
    public GuiExpression visit(final DivideBinary divideBinary) {
        GuiExpression left = divideBinary.getLeft().accept(this);
        GuiExpression right = divideBinary.getRight().accept(this);
        return new GuiDivideBinary(left, right);
    }

    @Override
    public GuiExpression visit(final EqBinary eqBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final NEqBinary nEqBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GTBinary gtBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final GTEBinary gteBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final LTBinary ltBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final LTEBinary lteBinary) {
        return null;
    }

    @Override
    public GuiExpression visit(final BangUnary bangUnary) {
        return null;
    }

    @Override
    public GuiExpression visit(final NegUnary negUnary) {
        return null;
    }

    @Override
    public GuiExpression visit(final BooleanLiteral booleanValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final DecimalLiteral decimalValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final DateLiteral dateLiteral) {
        return null;
    }

    @Override
    public GuiExpression visit(final MoneyLiteral moneyValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final IntegerLiteral integerValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final StringLiteral stringValue) {
        return null;
    }

    @Override
    public GuiExpression visit(final IdentifierLiteral identifierValue) {
        return null;
    }
}
