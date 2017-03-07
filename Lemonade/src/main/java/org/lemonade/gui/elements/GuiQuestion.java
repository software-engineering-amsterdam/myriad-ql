package org.lemonade.gui.elements;

import org.lemonade.nodes.expressions.literal.IdentifierLiteral;
import org.lemonade.nodes.types.QLType;

/**
 *
 */
public abstract class GuiQuestion implements GuiElement {

    private IdentifierLiteral identifier;
    private QLType type;
    private GuiValue value;

    public GuiQuestion(IdentifierLiteral identifier, QLType type) {
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public void update() {
        value.update();
    }
}
