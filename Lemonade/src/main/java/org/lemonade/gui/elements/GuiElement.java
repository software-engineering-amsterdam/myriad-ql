package org.lemonade.gui.elements;

import org.lemonade.visitors.ASTVisitor;

import javafx.scene.control.Control;

/**
 *
 */
public interface GuiElement {

    void update();

    Control getWidget();

}
