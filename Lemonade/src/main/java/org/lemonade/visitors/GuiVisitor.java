package org.lemonade.visitors;

import java.time.LocalDate;
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
import org.lemonade.gui.expressions.binary.GuiEqBinary;
import org.lemonade.gui.expressions.binary.GuiGTBinary;
import org.lemonade.gui.expressions.binary.GuiGTEBinary;
import org.lemonade.gui.expressions.binary.GuiLTBinary;
import org.lemonade.gui.expressions.binary.GuiLTEBinary;
import org.lemonade.gui.expressions.binary.GuiMinusBinary;
import org.lemonade.gui.expressions.binary.GuiNEqBinary;
import org.lemonade.gui.expressions.binary.GuiOrBinary;
import org.lemonade.gui.expressions.binary.GuiPlusBinary;
import org.lemonade.gui.expressions.binary.GuiProductBinary;
import org.lemonade.gui.expressions.unary.GuiBangUnary;
import org.lemonade.gui.expressions.unary.GuiNegUnary;
import org.lemonade.gui.values.GuiBooleanValue;
import org.lemonade.gui.values.GuiDateValue;
import org.lemonade.gui.values.GuiDecimalValue;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.gui.values.GuiIntegerValue;
import org.lemonade.gui.values.GuiMoneyValue;
import org.lemonade.gui.values.GuiStringValue;
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

import javafx.geometry.Insets;
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
            bodies.add(body.accept(this));
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
        gridPane.setPadding(new Insets(3, 6, 3, 6));
        //        gridPane.setMaxWidth(560);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);

        GridPane.setConstraints(labelElement.getWidget(), 0, 0);
        GridPane.setConstraints(element.getWidget(), 1, 0);
        gridPane.getChildren().addAll(labelElement.getWidget(), element.getWidget());

        labelElement.getWidget().managedProperty().bind(labelElement.getWidget().visibleProperty());
        element.getWidget().managedProperty().bind(element.getWidget().visibleProperty());

        pane.addRow(rowCount, gridPane);

        return guiQuestion;
    }

    @Override
    public GuiBody visit(final Conditional conditional) {
        List<GuiBody> conditionalBodies = new ArrayList<>();
        for (Body body : conditional.getBodies()) {
            conditionalBodies.add(body.accept(this));
        }

        GuiExpression expression = conditional.getCondition().accept(this);
        GuiConditional guiConditional = new GuiConditional(conditionalBodies, expression);
        guiConditional.isVisible(false);
        return guiConditional;
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
        GuiExpression left = eqBinary.getLeft().accept(this);
        GuiExpression right = eqBinary.getRight().accept(this);
        return new GuiEqBinary(left, right);
    }

    @Override
    public GuiExpression visit(final NEqBinary nEqBinary) {
        GuiExpression left = nEqBinary.getLeft().accept(this);
        GuiExpression right = nEqBinary.getRight().accept(this);
        return new GuiNEqBinary(left, right);
    }

    @Override
    public GuiExpression visit(final GTBinary gtBinary) {
        GuiExpression left = gtBinary.getLeft().accept(this);
        GuiExpression right = gtBinary.getRight().accept(this);
        return new GuiGTBinary(left, right);
    }

    @Override
    public GuiExpression visit(final GTEBinary gteBinary) {
        GuiExpression left = gteBinary.getLeft().accept(this);
        GuiExpression right = gteBinary.getRight().accept(this);
        return new GuiGTEBinary(left, right);
    }

    @Override
    public GuiExpression visit(final LTBinary ltBinary) {
        GuiExpression left = ltBinary.getLeft().accept(this);
        GuiExpression right = ltBinary.getRight().accept(this);
        return new GuiLTBinary(left, right);
    }

    @Override
    public GuiExpression visit(final LTEBinary lteBinary) {
        GuiExpression left = lteBinary.getLeft().accept(this);
        GuiExpression right = lteBinary.getRight().accept(this);
        return new GuiLTEBinary(left, right);
    }

    @Override
    public GuiExpression visit(final BangUnary bangUnary) {
        GuiExpression expression = bangUnary.getExpression().accept(this);
        return new GuiBangUnary(expression);
    }

    @Override
    public GuiExpression visit(final NegUnary negUnary) {
        GuiExpression expression = negUnary.getExpression().accept(this);
        return new GuiNegUnary(expression);
    }

    @Override
    public GuiExpression visit(final BooleanLiteral booleanValue) {
        Boolean value = booleanValue.getValue();
        return new GuiBooleanValue(value);
    }

    @Override
    public GuiExpression visit(final DecimalLiteral decimalValue) {
        Double value = decimalValue.getValue();
        return new GuiDecimalValue(value);
    }

    @Override
    public GuiExpression visit(final DateLiteral dateValue) {
        LocalDate value = dateValue.getValue();
        return new GuiDateValue(value);
    }

    @Override
    public GuiExpression visit(final MoneyLiteral moneyValue) {
        Double value = moneyValue.getValue();
        return new GuiMoneyValue(value);
    }

    @Override
    public GuiExpression visit(final IntegerLiteral integerValue) {
        Integer value = integerValue.getValue();
        return new GuiIntegerValue(value);
    }

    @Override
    public GuiExpression visit(final StringLiteral stringValue) {
        String value = stringValue.getValue();
        return new GuiStringValue(value);
    }

    @Override
    public GuiExpression visit(final IdentifierLiteral identifierValue) {
        String value = identifierValue.getValue();
        return new GuiIdentifierValue(value);
    }
}
