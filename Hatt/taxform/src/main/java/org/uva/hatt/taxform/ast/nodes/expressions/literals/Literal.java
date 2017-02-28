package org.uva.hatt.taxform.ast.nodes.expressions.literals;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;

abstract class Literal extends Expression{

    private String id;

    Literal(int lineNumber, String id) {
        super(lineNumber);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
