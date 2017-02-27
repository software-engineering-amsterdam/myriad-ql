package org.ql.ast;

public class Identifier extends AbstractNode {
    private final String id;

    public Identifier(String id) {
        this.id = id;
    }

    public boolean equals(Identifier obj) {
        return id.equals(obj.toString());
    }

    @Override
    public String toString() {
        return id;
    }
}
