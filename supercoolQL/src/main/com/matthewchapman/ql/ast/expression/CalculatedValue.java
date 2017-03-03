package com.matthewchapman.ql.ast.expression;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.QLVisitor;
import com.matthewchapman.ql.validation.Visitable;

/**
 * Created by matt on 27/02/2017.
 *
 * Calculated value class, exists in a question that is not user answerable.
 * parameterGroup contains expressions to be evaluated to provide a result.
 */
public class CalculatedValue extends Expression implements Visitable {

    private final ParameterGroup parameterGroup;

    public CalculatedValue(ParameterGroup e) {
        this.parameterGroup = e;
    }

    public ParameterGroup getParameterGroup() {
        return this.parameterGroup;
    }

    @Override
    public <T> T accept(QLVisitor<T> visitor) {
        return null;
    }
}
