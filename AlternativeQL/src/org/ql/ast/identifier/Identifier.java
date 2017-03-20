package org.ql.ast.identifier;

import org.ql.ast.Node;

public class Identifier extends Node {
    private final String id;

    public Identifier(String id) {
        this.id = id;
    }

    public boolean equals(Identifier another) {
        return another.id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
