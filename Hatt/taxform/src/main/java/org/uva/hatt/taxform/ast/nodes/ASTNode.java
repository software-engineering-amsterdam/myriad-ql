package org.uva.hatt.taxform.ast.nodes;

public abstract class ASTNode {
    private final int lineNumber;

    public ASTNode(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
