package org.lemonade.visitors;

import java.util.ArrayList;
import java.util.List;

import org.lemonade.gui.GuiBody;
import org.lemonade.gui.values.GuiBooleanValue;
import org.lemonade.gui.GuiConditional;
import org.lemonade.gui.values.GuiDateValue;
import org.lemonade.gui.values.GuiDecimalValue;
import org.lemonade.gui.GuiElement;
import org.lemonade.gui.GuiForm;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.gui.values.GuiIntegerValue;
import org.lemonade.gui.values.GuiLabelValue;
import org.lemonade.gui.values.GuiMoneyValue;
import org.lemonade.gui.GuiQuestion;
import org.lemonade.gui.values.GuiStringValue;
import org.lemonade.gui.values.GuiValue;
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

import javafx.scene.layout.GridPane;

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
