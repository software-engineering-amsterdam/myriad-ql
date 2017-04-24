package org.lemonade.nodes;

import java.util.List;

import org.lemonade.nodes.expressions.Expression;
import org.lemonade.visitors.interfaces.BaseVisitor;

public class Conditional extends Body {

    private Expression condition;
    private List<Body> bodies;

    public Conditional(Expression expr, List<Body> bodies) {
        this.condition = expr;
        this.bodies = bodies;
    }

    public List<Body> getBodies() {
        return bodies;
    }

    public Expression getCondition() {
        return condition;
    }

    @Override
    public <T> T accept(BaseVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isQuestion() {
        return false;
    }

    @Override
    public boolean isConditional() {
        return true;
    }
}
