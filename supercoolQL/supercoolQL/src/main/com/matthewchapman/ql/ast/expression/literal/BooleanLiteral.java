package com.matthewchapman.ql.ast.expression.literal;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.visitor.QLExpressionVisitor;

/**
 * Created by matt on 27/02/2017.
 * <p>
 * Boolean literal type implementation
 */
public class BooleanLiteral extends Expression {

    private final Boolean value;

    public BooleanLiteral(String value, int line, int column) {
        this.value = Boolean.parseBoolean(value);
        super.setLine(line);
        super.setColumn(column);
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

    @Override
    public <T, C> T accept(QLExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }

}
