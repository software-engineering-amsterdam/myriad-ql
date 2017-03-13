package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.node.operators.Operator;

public class CalculationBinaryExpressionNode extends BinaryExpressionNode {
    private Operator mOperator;

    public CalculationBinaryExpressionNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }

    public CalculationBinaryExpressionNode(ExpressionNode left, Operator operator, ExpressionNode right) {
        super(left, operator, right);
        mOperator = operator;
    }

    @Override
    public boolean isBoolean() {
        return mOperator.equals(">") ||
                mOperator.equals("<") ||
                mOperator.equals("<=") ||
                mOperator.equals(">=");
    }
}
