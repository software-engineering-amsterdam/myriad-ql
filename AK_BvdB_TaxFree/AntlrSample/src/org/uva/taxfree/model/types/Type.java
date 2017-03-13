package org.uva.taxfree.model.types;

import org.uva.taxfree.model.node.operators.AddOperator;

public abstract class Type {
    public boolean equals(Type other) {
        return getClass().equals(other.getClass());
    }

    public abstract boolean supports(AddOperator add);
}
