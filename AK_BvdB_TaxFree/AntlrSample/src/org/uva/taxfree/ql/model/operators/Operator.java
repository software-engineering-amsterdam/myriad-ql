package org.uva.taxfree.ql.model.operators;

import org.uva.taxfree.ql.model.node.expression.ExpressionNode;
import org.uva.taxfree.ql.model.types.Type;

public abstract class Operator {
    public abstract boolean supports(Type left, Type right);

    public abstract Type getType();

    public abstract String evaluate(ExpressionNode left, ExpressionNode right);
}
