package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;

import java.util.List;

public class IfThen extends Item{

    private final Expression condition;
    private final List<Item> thenStatements;

    public IfThen(int lineNumber, Expression condition, List<Item> thenStatements) {
        super(lineNumber);
        this.condition = condition;
        this.thenStatements = thenStatements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Item> getThenStatements() {
        return thenStatements;
    }

    @Override
    public <T> T accept(ItemVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
