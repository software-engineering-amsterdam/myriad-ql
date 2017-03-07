package org.lemonade.gui.elements;

import org.lemonade.nodes.expressions.literal.IdentifierLiteral;

/**
 *
 */
public class GuiQuestion implements GuiElement {

    private IdentifierLiteral identifier;
    private GuiValue<?> value;

    public GuiQuestion(IdentifierLiteral identifier, GuiValue<?> value) {
        this.identifier = identifier;
        this.value = value;
    }

    @Override
    public void update() {
        value.update();
    }
}
