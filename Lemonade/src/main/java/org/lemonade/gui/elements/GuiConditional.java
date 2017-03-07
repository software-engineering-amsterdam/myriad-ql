package org.lemonade.gui.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Control;

public class GuiConditional extends GuiBody {

    private GuiIdentifierValue dependentIdentifier;
    private boolean isVisible;
    private List<GuiBody> bodies;

    public GuiConditional(GuiIdentifierValue dependentIdentifier) {
        this.dependentIdentifier = dependentIdentifier;
        this.isVisible = false;
        this.bodies = new ArrayList<>();
    }

    @Override
    public void update() {
        for (GuiBody body : bodies) {
            body.update();
        }
    }

    @Override
    public Control getWidget() {
        return null;
    }
}
