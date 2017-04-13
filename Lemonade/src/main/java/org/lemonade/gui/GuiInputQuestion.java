package org.lemonade.gui;

import org.lemonade.gui.elements.GuiLabelElement;
import org.lemonade.gui.elements.GuiMutableElement;
import org.lemonade.gui.values.GuiIdentifierValue;

public class GuiInputQuestion extends GuiQuestion {

    private GuiMutableElement element;

    public GuiInputQuestion(GuiIdentifierValue identifier, GuiLabelElement labelElement, GuiMutableElement element) {
        super(identifier, labelElement);
        this.element = element;
    }

    @Override
    public GuiMutableElement getElement() {
        return element;
    }

}
