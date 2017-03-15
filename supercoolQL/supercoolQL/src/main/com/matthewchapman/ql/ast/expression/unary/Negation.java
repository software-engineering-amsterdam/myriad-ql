package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.QLVisitable;
import com.matthewchapman.ql.validation.visitor.QLVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Binary negation class
 */
public class Negation extends UnaryOperation implements QLVisitable {

    //TODO Implement Negation

    private final Expression expression;

    public Negation(Expression expression, int line, int column) {
        this.expression = expression;
        super.setLine(line);
        super.setColumn(column);
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public String toString() {
        return "!(" + getExpression() + ")";
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
