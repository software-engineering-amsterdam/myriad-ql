package org.ql.ast;

public class Identifier extends AbstractNode {
    private final String id;

    public Identifier(String id, Metadata metadata) {
        this.id = id;
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
