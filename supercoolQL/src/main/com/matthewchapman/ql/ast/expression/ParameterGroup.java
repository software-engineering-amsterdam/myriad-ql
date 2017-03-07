package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;
import com.matthewchapman.ql.validation.Visitable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 24/02/2017.
 *
 * Contains multiple expressions to be evaluated in one block and return a single result
 */

public class ParameterGroup extends Expression implements Visitable {

    //TODO implement ParameterGroup
    private final List<Expression> expressions;

    public ParameterGroup() {
        this.expressions = new ArrayList<>();
    }

    public List<Expression> getParameters() {
        return this.expressions;
    }

    public void addExpression(Expression e) {
        this.expressions.add(e);
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
