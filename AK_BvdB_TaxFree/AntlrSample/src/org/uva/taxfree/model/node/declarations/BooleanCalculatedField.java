package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.node.expression.ConditionNode;
import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.Type;

public class BooleanCalculatedField extends CalculatedField {
    public BooleanCalculatedField(String label, String id, ConditionNode condition) {
        super(label, id, condition);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
