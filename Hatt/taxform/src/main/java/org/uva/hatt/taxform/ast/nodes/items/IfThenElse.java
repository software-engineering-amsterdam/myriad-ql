package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;

import java.util.List;

public class IfThenElse extends IfThen {

    private final List<Item> elseStatements;

    public IfThenElse(int lineNumber, Expression condition, List<Item> thenStatements, List<Item> elseStatements) {
        super(lineNumber, condition, thenStatements);
        this.elseStatements = elseStatements;
    }

    public List<Item> getElseStatements() {
        return elseStatements;
    }

    @Override
    public <T> T accept(ItemVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
