package com.matthewchapman.ql.ast.expression.literal;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.visitors.QLExpressionVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * String literal type implementation.
 */
public class StringLiteral extends Expression {

    private final String value;

    public StringLiteral(String value, int line, int charPositionInLine) {
        this.value = value;
        super.setLine(line);
        super.setColumn(charPositionInLine);
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public <T, C> T accept(QLExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
