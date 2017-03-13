package org.lemonade.gui.elements;

import java.util.List;

import org.lemonade.visitors.EvaluateVisitor;

import javafx.scene.control.Control;

public class GuiConditional extends GuiBody {

    private boolean isVisible;
    private List<GuiBody> bodies;

    // TODO: add expression?
    public GuiConditional(List<GuiBody> bodies) {
        this.isVisible = false;
        this.bodies = bodies;
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

    @Override
    public boolean isConditional() {
        return true;
    }

    public GuiConditional accept(EvaluateVisitor visitor) {
        return visitor.visit(this);
    }

}
