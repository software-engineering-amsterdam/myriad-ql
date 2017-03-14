package org.lemonade.visitors;

<<<<<<< Updated upstream
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
=======
import javafx.scene.layout.GridPane;
import org.lemonade.gui.*;
import org.lemonade.gui.values.*;
>>>>>>> Stashed changes
import org.lemonade.nodes.Body;
import org.lemonade.nodes.Conditional;
import org.lemonade.nodes.Form;
import org.lemonade.nodes.Question;
import org.lemonade.nodes.types.*;
import org.lemonade.visitors.interfaces.BaseVisitor;
import org.lemonade.visitors.interfaces.TypeVisitor;

<<<<<<< Updated upstream
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> Stashed changes

public class GuiVisitor implements BaseVisitor<GuiBody>, TypeVisitor<GuiElement> {

    private GridPane pane;
    private int rowCount;

    public GuiVisitor(GridPane pane) {
        this.pane = pane;
        this.rowCount = 0;
    }

    @Override
<<<<<<< Updated upstream
    public GuiBody visit(final Form form) {
=======
    public GuiElement visit(final Form form) {
>>>>>>> Stashed changes
        List<GuiBody> bodies = new ArrayList<>();
        GuiIdentifierValue identifier = new GuiIdentifierValue(form.getIdentifier().getValue());

        for (Body body : form.getBodies()) {
            bodies.add((GuiBody) body.accept(this));
            rowCount++;
        }
        return new GuiForm(identifier, bodies);
    }

    @Override
<<<<<<< Updated upstream
    public GuiBody visit(final Question question) {
=======
    public GuiElement visit(final Question question) {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    public GuiBody visit(final Conditional conditional) {
=======
    public GuiElement visit(final Conditional conditional) {
>>>>>>> Stashed changes
        List<GuiBody> conditionalBodies = new ArrayList<>();
        for (Body body : conditional.getBodies()) {
            conditionalBodies.add(body.accept(this));
        }
        return new GuiConditional(conditionalBodies);
    }

    @Override
    public GuiElement visit(final QLIntegerType qlIntegerType) {
<<<<<<< Updated upstream
        return new GuiIntegerElement();
=======
        return new GuiIntegerValue();
>>>>>>> Stashed changes
    }

    @Override
    public GuiElement visit(final QLBooleanType qlBooleanType) {
<<<<<<< Updated upstream
        return new GuiBooleanElement();
=======
        return new GuiBooleanValue();
>>>>>>> Stashed changes
    }

    @Override
    public GuiElement visit(final QLDateType qlDateType) {
<<<<<<< Updated upstream
        return new GuiDateElement();
=======
        return new GuiDateValue();
>>>>>>> Stashed changes
    }

    @Override
    public GuiElement visit(final QLDecimalType qlDecimalType) {
<<<<<<< Updated upstream
        return new GuiDecimalElement();
=======
        return new GuiDecimalValue();
>>>>>>> Stashed changes
    }

    @Override
    public GuiElement visit(final QLMoneyType qlMoneyType) {
<<<<<<< Updated upstream
        return new GuiMoneyElement();
=======
        return new GuiMoneyValue();
>>>>>>> Stashed changes
    }

    @Override
    public GuiElement visit(final QLStringType qlStringType) {
<<<<<<< Updated upstream
        return new GuiStringElement();
=======
        return new GuiStringValue();
>>>>>>> Stashed changes
    }

}
