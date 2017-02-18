package org.lemonade.expression;

import org.lemonade.QLOperatorType;
import org.lemonade.QLType;

//public class BinaryExpression extends Expression {
//    private int right;
//    private QLOperatorType.BinaryOperator operator;
//
//    public BinaryExpression(QLType type, QLOperatorType.BinaryOperator operator, int left, int right) {
//        super(type, left);
//        this.right = right;
//        this.operator = operator;
//    }
//
//    @Override
//    public String toString() {
//        return Integer.toString(super.getLeft()) + " " + operator.toString() + " " + Integer.toString(this.right);
//    }
//}

public class BinaryExpression implements Expression {
    private QLOperatorType operator;
    private Expression left;
    private Expression right;

    public BinaryExpression(QLOperatorType operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public QLOperatorType getOperator() {
        return this.operator;
    }

    public Expression getLeft() {
        return this.left;
    }

    public Expression getRight() {
        return this.right;
    }

}
