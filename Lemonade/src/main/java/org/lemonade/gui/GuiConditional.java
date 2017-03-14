package org.lemonade.gui;

import java.util.List;

import org.lemonade.visitors.EvaluateVisitor;

public class GuiConditional extends GuiBody {

    private GuiExpression condition;
    private boolean isVisible;
    private List<GuiBody> bodies;

    public GuiConditional(List<GuiBody> bodies, GuiExpression expression) {
        this.isVisible = false;
        this.bodies = bodies;
        this.condition = expression;
    }

    public GuiExpression getCondition() {
        return condition;
    }

    public List<GuiBody> getBodies() {
        return bodies;
    }

    @Override
    public boolean isConditional() {
        return true;
    }

    public void accept(EvaluateVisitor visitor) {
        visitor.visit(this);
    }

}
