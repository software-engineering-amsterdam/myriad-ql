package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.ast.QLVisitable;
import com.matthewchapman.ql.validation.QLVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Binary negation class
 */
public class Negation extends UnaryOperation implements QLVisitable {

    //TODO Implement Negation

    private final Expression expression;

    public Negation(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public String toString() {
        return "!(" + getExpression().toString() + ")";
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
