package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.visitors.Visitor;

import java.util.List;

public class IfThenElse extends ASTNode implements Item{

    private final Expression condition;
    private final List<Item> thenStatements;
    private final List<Item> elseStatements;

    public IfThenElse(int lineNumber, Expression condition, List<Item> thenStatements, List<Item> elseStatements) {
        super(lineNumber);
        this.condition = condition;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Item> getThenStatements() {
        return thenStatements;
    }

    public List<Item> getElseStatements() {
        return elseStatements;
    }

    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
