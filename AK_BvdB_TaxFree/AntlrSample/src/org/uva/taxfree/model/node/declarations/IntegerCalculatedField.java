package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.types.IntegerType;
import org.uva.taxfree.model.types.Type;

public class IntegerCalculatedField extends CalculatedField {
    public IntegerCalculatedField(String label, String id, ExpressionNode condition) {
        super(label, id, condition);
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}



