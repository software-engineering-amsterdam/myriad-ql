package org.lemonade.gui.elements;

import javafx.scene.control.Control;
import org.lemonade.gui.values.GuiValue;

/**
 *
 */
public interface GuiElement {

    GuiValue<?> getValue();

    Control getWidget();

    void update();

}
