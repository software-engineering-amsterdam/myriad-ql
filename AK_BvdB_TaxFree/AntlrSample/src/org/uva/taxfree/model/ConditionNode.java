package org.uva.taxfree.model;

import org.uva.taxfree.util.Evaluator;

import javax.script.ScriptException;

public abstract class ConditionNode extends Node {

    public String evaluate() {
        try {
            return tryEvaluate();
        } catch (ScriptException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred whilst evaluating " + toString());
        }
    }

    // Allows the typeChecker to perform a testrun on all expressions.
    public String tryEvaluate() throws ScriptException {
        return Evaluator.calculate(toString());
    }
}
