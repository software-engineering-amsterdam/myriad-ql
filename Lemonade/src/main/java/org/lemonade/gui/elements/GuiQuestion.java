package org.lemonade.gui.elements;

import org.lemonade.nodes.expressions.literal.IdentifierLiteral;
import org.lemonade.nodes.types.QLType;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

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
//        if (widgetType.equals(WidgetType.CHECKBOX)) {
//            boolean isSelected = ((CheckBox) widget).isSelected();
//
//        } else {
//            String text = ((TextField) widget).getText();
//            ((GuiStringValue) value).setValue(text);
//        }

    }
}
