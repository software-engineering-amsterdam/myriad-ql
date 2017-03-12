package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.types.Type;
import org.uva.taxfree.util.Evaluator;

import javax.script.ScriptException;

public abstract class ExpressionNode extends Node {

    public String evaluate() {
        try {
            return tryEvaluate();
        } catch (ScriptException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred whilst evaluating " + toString());
        }
    }

    // Allows the typeChecker to perform a test run on all expressions.
    public String tryEvaluate() throws ScriptException {
        return Evaluator.calculate(resolveValue());
    }

    public abstract String resolveValue();

    public abstract boolean isValid();

    public boolean isBoolean() {
        boolean isTrue = "true".equals(evaluate());
        boolean isFalse = "false".equals(evaluate());
        return isTrue || isFalse;
    }

    public boolean isSameType(ExpressionNode other) {
        return getType().equals(other.getType());
    }

    public abstract Type getType();
}
