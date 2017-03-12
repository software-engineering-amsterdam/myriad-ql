package org.lemonade.gui.elements;

import java.util.List;

import javafx.scene.control.Control;

public class GuiForm implements GuiElement {

    private GuiIdentifierValue identifier;
    private List<GuiBody> bodies;

    public GuiForm(GuiIdentifierValue identifier, List<GuiBody> bodies) {
        this.identifier = identifier;
        this.bodies = bodies;
    }

    @Override public void update() {

    }

    @Override public Control getWidget() {
        return null;
    }
}
