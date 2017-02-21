package org.uva.hatt.taxform.ast.nodes.items;

import org.uva.hatt.taxform.ast.nodes.ASTNode;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;

import java.util.List;

public class Conditional extends ASTNode implements Item{

    private Expression condition;
    private List<Question> thenStatements;
    private List<Question> elseStatements;

    public Conditional(int lineNumber) {
        super(lineNumber);
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public List<Question> getThenStatements() {
        return thenStatements;
    }

    public void setThenStatements(List<Question> thenStatements) {
        this.thenStatements = thenStatements;
    }

    public List<Question> getElseStatements() {
        return elseStatements;
    }

    public void setElseStatements(List<Question> elseStatements) {
        this.elseStatements = elseStatements;
    }
}
