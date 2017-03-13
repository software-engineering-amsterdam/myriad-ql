package org.lemonade.visitors;

import java.util.ArrayList;
import java.util.List;

import org.lemonade.gui.elements.GuiBody;
import org.lemonade.gui.elements.GuiBooleanValue;
import org.lemonade.gui.elements.GuiConditional;
import org.lemonade.gui.elements.GuiDateValue;
import org.lemonade.gui.elements.GuiDecimalValue;
import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.GuiForm;
import org.lemonade.gui.elements.GuiIdentifierValue;
import org.lemonade.gui.elements.GuiIntegerValue;
import org.lemonade.gui.elements.GuiLabelValue;
import org.lemonade.gui.elements.GuiMoneyValue;
import org.lemonade.gui.elements.GuiQuestion;
import org.lemonade.gui.elements.GuiStringValue;
import org.lemonade.gui.elements.GuiValue;
import org.lemonade.nodes.ASTNode;
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

import javafx.scene.layout.GridPane;
import org.lemonade.visitors.interfaces.BaseVisitor;
import org.lemonade.visitors.interfaces.ExpressionVisitor;
import org.lemonade.visitors.interfaces.LiteralVisitor;
import org.lemonade.visitors.interfaces.TypeVisitor;

public class GuiVisitor implements BaseVisitor<GuiElement>, TypeVisitor<GuiElement> {

    private GridPane pane;
    private int rowCount;

    public GuiVisitor(GridPane pane) {
        this.pane = pane;
        this.rowCount = 0;
    }

    @Override public GuiElement visit(final Form form) {
        List<GuiBody> bodies = new ArrayList<>();
        GuiIdentifierValue identifier = new GuiIdentifierValue(form.getIdentifier().getValue());

        for (Body body : form.getBodies()) {
            bodies.add((GuiBody) body.accept(this));
            rowCount++;
        }
        return new GuiForm(identifier, bodies);
    }

    @Override public GuiElement visit(final Question question) {
        GuiIdentifierValue identifier = new GuiIdentifierValue(question.getIdentifier().getValue());
        GuiValue<?> value = (GuiValue<?>) question.getType().accept(this);
        GuiLabelValue labelValue = new GuiLabelValue(question.getLabel());
        GuiQuestion guiQuestion = new GuiQuestion(identifier, labelValue, value);

        final GridPane gridPane = new GridPane();
        gridPane.setHgap(6);
        gridPane.setVgap(6);
        GridPane.setConstraints(guiQuestion.getLabelValue().getWidget(), 0, 0);
        GridPane.setConstraints(guiQuestion.getValue().getWidget(), 1, 0);
        gridPane.getChildren().addAll(guiQuestion.getLabelValue().getWidget(), guiQuestion.getValue().getWidget());

        gridPane.managedProperty().bind(gridPane.visibleProperty());

        pane.addRow(rowCount, gridPane);

        return guiQuestion;
    }

    // TODO: figure out how to store related identifiers
    @Override public GuiElement visit(final Conditional conditional) {
        List<GuiBody> conditionalBodies = new ArrayList<>();
        for (Body body : conditional.getBodies()) {
            conditionalBodies.add((GuiBody) body.accept(this));
        }
        return new GuiConditional(conditionalBodies);
    }

    @Override public GuiElement visit(final QLIntegerType qlIntegerType) {
        return new GuiIntegerValue();
    }

    @Override public GuiElement visit(final QLBooleanType qlBooleanType) {
        return new GuiBooleanValue();
    }

    @Override public GuiElement visit(final QLDateType qlDateType) {
        return new GuiDateValue();
    }

    @Override public GuiElement visit(final QLDecimalType qlDecimalType) {
        return new GuiDecimalValue();
    }

    @Override public GuiElement visit(final QLMoneyType qlMoneyType) {
        return new GuiMoneyValue();
    }

    @Override public GuiElement visit(final QLStringType qlStringType) {
        return new GuiStringValue();
    }

}
