package org.ql.ast;

public class Identifier {
    private final String id;

    public Identifier(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
