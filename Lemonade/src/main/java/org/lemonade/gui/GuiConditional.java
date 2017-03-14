package org.lemonade.gui;

import javafx.scene.control.Control;
import org.lemonade.visitors.EvaluateVisitor;

import java.util.List;

public class GuiConditional extends GuiBody {

    private GuiExpression expression;
    private boolean isVisible;
    private List<GuiBody> bodies;

    // TODO: add expression?
    public GuiConditional(List<GuiBody> bodies) {
        this.isVisible = false;
        this.bodies = bodies;
//        this.expression = new GuiExpression();
    }

    @Override
    public boolean isConditional() {
        return true;
    }

    public void accept(EvaluateVisitor visitor) {
        visitor.visit(this);
    }

}
