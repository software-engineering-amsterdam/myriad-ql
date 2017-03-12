package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.node.operators.AddOperator;
import org.uva.taxfree.model.node.operators.Operator;
import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.Type;

public class BooleanBinaryExpressionNode extends BinaryExpressionNode {

    public BooleanBinaryExpressionNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }

    public BooleanBinaryExpressionNode(ExpressionNode left, Operator operator, ExpressionNode right) {
        super(left, operator, right);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
