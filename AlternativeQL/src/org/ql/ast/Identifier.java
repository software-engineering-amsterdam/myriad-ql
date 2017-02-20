package org.ql.ast;

public class Identifier implements Node {
    private final String id;

    public Identifier(String id) {
        this.id = id;
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
