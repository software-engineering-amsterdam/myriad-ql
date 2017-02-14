package org.ql.ast.expression;

import org.ql.ast.Expression;

public class Parameter implements Expression {
    private final String id;

    public Parameter(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
