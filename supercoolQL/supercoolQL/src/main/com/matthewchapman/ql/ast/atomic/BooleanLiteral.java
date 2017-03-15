package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;

/**
 * Created by matt on 27/02/2017.
 * <p>
 * Boolean literal type implementation
 */
public class BooleanLiteral extends Expression {

    private final Boolean value;

    public BooleanLiteral(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    public boolean getValue() { return this.value; }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
