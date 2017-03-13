package org.lemonade.gui;

import java.util.List;

import org.lemonade.visitors.EvaluateVisitor;

import javafx.scene.control.Control;

public class GuiConditional extends GuiBody {

    private GuiExpression expression;
    private boolean isVisible;
    private List<GuiBody> bodies;

    // TODO: add expression?
    public GuiConditional(List<GuiBody> bodies) {
        this.isVisible = false;
        this.bodies = bodies;
        this.expression = new GuiExpression();
    }

    @Override
    public boolean isConditional() {
        return true;
    }

    public GuiConditional accept(EvaluateVisitor visitor) {
        return visitor.visit(this);
    }

}
