package com.matthewchapman.ql.ast.expression.literal;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.visitors.ExpressionVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Integer Literal type implementation
 */
public class IntegerLiteral extends Expression {

    private final int value;

    public IntegerLiteral(String value, int line, int column) {
        this.value = Integer.parseInt(value);
        super.setLine(line);
        super.setColumn(column);
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
