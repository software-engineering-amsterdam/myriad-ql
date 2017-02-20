package org.ql.ast.expression;

import org.ql.ast.Expression;
import org.ql.ast.Metadata;
import org.ql.ast.Visitor;

public class Parameter extends Expression {
    private final String id;

    public Parameter(String id, Metadata metadata) {
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
