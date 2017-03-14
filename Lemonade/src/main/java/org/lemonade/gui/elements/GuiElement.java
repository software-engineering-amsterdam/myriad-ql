package org.lemonade.gui.elements;

import org.lemonade.gui.values.GuiValue;
import org.lemonade.visitors.UpdateVisitor;

import javafx.scene.control.Control;

/**
 *
 */
public interface GuiElement {

    GuiValue<?> getValue();

    Control getWidget();

    void update();

}
