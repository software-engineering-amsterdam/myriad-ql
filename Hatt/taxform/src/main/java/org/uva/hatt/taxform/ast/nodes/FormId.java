package org.uva.hatt.taxform.ast.nodes;

public class FormId extends ASTNode{

    private String identifier;

    public FormId(int lineNumber, String identifier) {
        super(lineNumber);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
