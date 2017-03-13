package org.lemonade.gui.elements;

import org.lemonade.visitors.EvaluateVisitor;

import javafx.scene.control.Control;

/**
 *
 */
public class GuiQuestion extends GuiBody {

    private GuiIdentifierValue identifier;

    private GuiLabelValue labelValue;
    private GuiValue<?> value;
    public GuiQuestion(GuiIdentifierValue identifier, GuiLabelValue labelValue, GuiValue<?> value) {
        this.identifier = identifier;
        this.labelValue = labelValue;
        this.value = value;
    }

    public GuiIdentifierValue getIdentifier() {
        return identifier;
    }

    public GuiLabelValue getLabelValue() {
        return labelValue;
    }

    public GuiValue<?> getValue() {
        return value;
    }

    @Override
    public void update() {
        value.update();
        System.err.println("new value = " + value);
    }

    @Override
    public Control getWidget() {
        return value.getWidget();
    }

    @Override
    public boolean isQuestion() {
        return true;
    }

    public GuiQuestion accept(EvaluateVisitor visitor) {
        return visitor.visit(this);
    }
}
