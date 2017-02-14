package org.ql.ast;

public class Parameter implements Node {
    private final String id;

    public Parameter(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
