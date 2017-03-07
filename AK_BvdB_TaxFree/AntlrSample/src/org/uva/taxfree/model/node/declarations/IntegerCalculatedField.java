package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.node.expression.ConditionNode;
import org.uva.taxfree.model.types.IntegerType;
import org.uva.taxfree.model.types.Type;

public class IntegerCalculatedField extends CalculatedField {
    public IntegerCalculatedField(String label, String id, ConditionNode condition) {
        super(label, id, condition);
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}



