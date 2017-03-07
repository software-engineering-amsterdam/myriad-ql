package org.uva.taxfree.model.node.expression;

public class CalculationBinaryExpressionNode extends BinaryExpressionNode {
    private String mOperator;
    public CalculationBinaryExpressionNode(ExpressionNode left, String operator, ExpressionNode right) {
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
