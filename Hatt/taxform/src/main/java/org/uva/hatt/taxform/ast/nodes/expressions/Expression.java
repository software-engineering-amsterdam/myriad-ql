package org.uva.hatt.taxform.ast.nodes.expressions;

import org.uva.hatt.taxform.ast.nodes.ASTNode;

public abstract class Expression extends ASTNode{

    public abstract <T> T accept(ExpressionVisitor<T> expressionVisitor);

    public Expression(int lineNumber) {
        super(lineNumber);
    }
}
