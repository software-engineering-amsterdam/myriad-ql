package org.lemonade.visitors;

import java.util.ArrayList;
import java.util.List;

import org.lemonade.gui.GuiBody;
import org.lemonade.gui.GuiConditional;
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

public class GuiVisitor implements BaseVisitor<GuiBody>, TypeVisitor<GuiElement>, ExpressionVisitor<GuiElement> {

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
    public GuiElement visit(final AndBinary andBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final OrBinary orBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final PlusBinary plusBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final ProductBinary productBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final MinusBinary minusBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final DivideBinary divideBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final EqBinary eqBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final NEqBinary nEqBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final GTBinary gtBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final GTEBinary gteBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final LTBinary ltBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final LTEBinary lteBinary) {
        return null;
    }

    @Override
    public GuiElement visit(final BangUnary bangUnary) {
        return null;
    }

    @Override
    public GuiElement visit(final NegUnary negUnary) {
        return null;
    }

    @Override
    public GuiElement visit(final BooleanLiteral booleanValue) {
        return null;
    }

    @Override
    public GuiElement visit(final DecimalLiteral decimalValue) {
        return null;
    }

    @Override
    public GuiElement visit(final DateLiteral dateLiteral) {
        return null;
    }

    @Override
    public GuiElement visit(final MoneyLiteral moneyValue) {
        return null;
    }

    @Override
    public GuiElement visit(final IntegerLiteral integerValue) {
        return null;
    }

    @Override
    public GuiElement visit(final StringLiteral stringValue) {
        return null;
    }

    @Override
    public GuiElement visit(final IdentifierLiteral identifierValue) {
        return null;

    }
}
