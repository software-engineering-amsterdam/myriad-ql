package org.uva.hatt.taxform.ast.nodes;

public class ASTNode {

    private int lineNumber;

    public ASTNode(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
