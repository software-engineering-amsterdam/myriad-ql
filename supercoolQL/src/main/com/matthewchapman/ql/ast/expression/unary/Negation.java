package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validator.QLVisitor;
import com.matthewchapman.ql.validator.Visitable;

/**
 * Created by matt on 24/02/2017.
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
