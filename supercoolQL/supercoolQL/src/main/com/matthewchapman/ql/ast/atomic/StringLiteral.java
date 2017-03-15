package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.visitor.QLVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * String literal type implementation.
 */
public class StringLiteral extends Expression {

    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
