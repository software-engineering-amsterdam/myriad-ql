package org.uva.hatt.taxform.ast.nodes;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public abstract class ASTNode {

    public abstract void accept(Visitor visitor);

    private int lineNumber;

    public ASTNode(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
