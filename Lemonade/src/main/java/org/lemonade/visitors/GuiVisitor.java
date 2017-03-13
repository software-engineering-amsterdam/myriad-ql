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
import org.lemonade.nodes.types.QLBooleanType;
import org.lemonade.nodes.types.QLDateType;
import org.lemonade.nodes.types.QLDecimalType;
import org.lemonade.nodes.types.QLIntegerType;
import org.lemonade.nodes.types.QLMoneyType;
import org.lemonade.nodes.types.QLStringType;
import org.lemonade.visitors.interfaces.BaseVisitor;
import org.lemonade.visitors.interfaces.TypeVisitor;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class GuiVisitor implements BaseVisitor<GuiBody>, TypeVisitor<GuiElement> {

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

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);
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

}
