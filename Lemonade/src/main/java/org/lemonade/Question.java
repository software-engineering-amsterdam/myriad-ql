package org.lemonade;

public class Question extends ASTNode {
    private String identifier;
    private String label;
    private QLType typeSpecifier;

    public Question (String identifier, String label, QLType typeSpecifier, int lineNo) {
        super(lineNo);
        this.identifier = identifier;
        this.label = label;
        this.typeSpecifier = typeSpecifier;
    }
}
