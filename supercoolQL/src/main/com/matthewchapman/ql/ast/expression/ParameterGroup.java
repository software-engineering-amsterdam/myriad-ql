package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validator.QLVisitor;
import com.matthewchapman.ql.validator.Visitable;

import java.util.ArrayList;

/**
 * Created by matt on 24/02/2017.
 */

public class ParameterGroup extends Expression implements Visitable {

    //TODO implement ParameterGroup
    private final ArrayList<Expression> expressions;

    public ParameterGroup() {
        this.expressions = new ArrayList<>();
    }

    public ArrayList<Expression> getParameters() {
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
