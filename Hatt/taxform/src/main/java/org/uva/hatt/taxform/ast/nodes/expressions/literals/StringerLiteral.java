package org.uva.hatt.taxform.ast.nodes.expressions.literals;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.visitors.Visitor;

public class StringerLiteral extends Expression{

    private final String value;

    public StringerLiteral(int lineNumber, String value) {
        super(lineNumber);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
