package org.lemonade.gui;

import java.util.List;

import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.EvaluateVisitor;

import javafx.scene.control.Control;

public class GuiForm implements GuiElement {

    private GuiIdentifierValue identifier;
    private List<GuiBody> bodies;

    public GuiForm(GuiIdentifierValue identifier, List<GuiBody> bodies) {
        this.identifier = identifier;
        this.bodies = bodies;
    }

    public List<GuiBody> getBodies() {
        return this.bodies;
    }

    @Override public void update() {
        for (GuiBody body : bodies) {
            if (body.isConditional()) {

            }
        }
    }

    @Override public Control getWidget() {
        return null;
    }

    public GuiForm accept(EvaluateVisitor visitor) {
        return visitor.visit(this);
    }

}
