package org.uva.taxfree.model.node.expression;

public class CalculationExpressionNode extends ExpressionNode {
    private String mOperator;
    public CalculationExpressionNode(ConditionNode left, String operator, ConditionNode right) {
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
