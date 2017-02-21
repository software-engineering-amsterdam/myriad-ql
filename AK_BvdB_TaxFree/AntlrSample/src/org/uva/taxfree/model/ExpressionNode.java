package org.uva.taxfree.model;

import org.uva.taxfree.util.Evaluator;

import javax.script.ScriptException;

public class ExpressionNode extends ConditionNode {
    private Node mLeft;
    private String mOperator;
    private Node mRight;

    public ExpressionNode(String operator) {
        mOperator = operator;
    }

    @Override
    public void addChild(Node node) {
        if (mLeft == null) {
            mLeft = node;
        } else if (mRight == null) {
            mRight = node;
        } else {
            // Error handling!
        }
    }

    public String evaluate() {
        try {
            return tryEvaluate();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return "!! Error !!";
    }

    // Allows the typeChecker to perform a testrun on all expressions.
    public String tryEvaluate() throws ScriptException {
        return Evaluator.calculate(toString());
    }

    @Override
    public String toString() {
        return "(" + mLeft.toString() + mOperator + mRight.toString() + ")";
    }

}
