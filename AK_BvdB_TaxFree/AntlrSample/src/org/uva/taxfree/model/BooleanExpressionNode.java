package org.uva.taxfree.model;

public class BooleanExpressionNode extends ExpressionNode {
    public BooleanExpressionNode(String label) {
        super(label);
    }

    public boolean evaluate(){
        return ("true".equals(super.evaluate()));
    }
}
