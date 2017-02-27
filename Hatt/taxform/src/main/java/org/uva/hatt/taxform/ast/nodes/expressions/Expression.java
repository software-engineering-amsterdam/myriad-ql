package org.uva.hatt.taxform.ast.nodes.expressions;

import org.uva.hatt.taxform.ast.nodes.ASTNode;

public abstract class Expression extends ASTNode {

    private String expression;

    public Expression(int lineNumber) {
        super(lineNumber);
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
