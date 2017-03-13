package com.matthewchapman.ql.ast.atomic;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;

/**
 * Created by matt on 24/02/2017.
 *
 * Integer Literal type implementation
 */
public class IntegerLiteral extends Expression {

    private Integer value;

    public IntegerLiteral(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
