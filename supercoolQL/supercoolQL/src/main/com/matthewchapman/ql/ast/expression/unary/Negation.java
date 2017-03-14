package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;
import com.matthewchapman.ql.ast.QLVisitable;

/**
 * Created by matt on 24/02/2017.
 *
 * Binary negation class
 */
public class Negation extends UnaryOperation implements QLVisitable {

    //TODO Implement Negation

    private Expression expression;

    public Negation(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() { return this.expression; }

    @Override
    public <T> T accept(QLVisitor<T> visitor, String context) {
        return visitor.visit(this, context);
    }
}
