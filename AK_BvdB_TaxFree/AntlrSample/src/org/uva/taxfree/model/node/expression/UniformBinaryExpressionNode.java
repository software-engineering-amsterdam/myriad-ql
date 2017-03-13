package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.node.operators.Operator;

public class UniformBinaryExpressionNode extends BinaryExpressionNode {
    public UniformBinaryExpressionNode(ExpressionNode left, String operator, ExpressionNode right) {
        super(left, operator, right);
    }


    public UniformBinaryExpressionNode(ExpressionNode left, Operator operator, ExpressionNode right) {
        super(left, operator, right);
    }
}
