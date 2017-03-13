package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.types.Type;

public abstract class Operator {
    public abstract boolean supports(Type left, Type right);

}
