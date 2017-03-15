package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Integer Literal type implementation
 */
public class IntegerLiteral extends Expression {

    private final int value;

    public IntegerLiteral(String value) {
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
