package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;
import com.matthewchapman.ql.validation.Visitable;

/**
 * Created by matt on 24/02/2017.
 *
 * Binary negation class
 */
public class Negation extends UnaryOperation implements Visitable {

    //TODO Implement Negation

    public Negation(Expression expression) {
        super(expression);
    }


    @Override
    public <T> T accept(QLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
