package org.lemonade.gui.elements;

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

    public GuiLabelValue getLabelValue() {
        return labelValue;
    }

    public GuiValue<?> getValue() {
        return value;
    }

    @Override
    public void update() {
        value.update();
    }

    @Override
    public Control getWidget() {
        return value.getWidget();
    }
}
