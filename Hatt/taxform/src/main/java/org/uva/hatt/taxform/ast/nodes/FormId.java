package org.uva.hatt.taxform.ast.nodes;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public class FormId extends ASTNode{

    private String identifier;

    public FormId(int lineNumber, String identifier) {
        super(lineNumber);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
