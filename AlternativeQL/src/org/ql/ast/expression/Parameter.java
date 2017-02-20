package org.ql.ast.expression;

import org.ql.ast.Expression;
import org.ql.ast.Visitor;

public class Parameter implements Expression {
    private final String id;

    public Parameter(String id) {
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
