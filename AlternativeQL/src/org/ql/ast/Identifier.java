package org.ql.ast;

public class Identifier extends Node {
    private final String id;

    public Identifier(String id) {
        this.id = id;
    }

    public boolean equals(Identifier identifier) {
        return id.equals(identifier.toString());
    }

    @Override
    public String toString() {
        return id;
    }
}
