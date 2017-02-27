package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.Visitor;

/**
 * Created by matt on 24/02/2017.
 */
public class Negation extends UnaryOperation {

    //TODO Implement Negation

    public Negation(Expression expression)
    {
        super(expression);
    }

    public void accept(Visitor visitor) {
        visitor.visitNegation(this);
    }

}
