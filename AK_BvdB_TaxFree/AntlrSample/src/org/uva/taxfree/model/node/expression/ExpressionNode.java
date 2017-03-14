package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.types.Type;
import org.uva.taxfree.util.Evaluator;

import javax.script.ScriptException;
import java.util.List;
import java.util.Set;

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

    public boolean isSameType(ExpressionNode other) {
        Type thisType = getType();
        Type otherType = other.getType();
        return thisType.equals(otherType);
    }

    public abstract Type getType();

    public abstract void getDependencies(Set<String> dependencies);

    @Override
    public void fillQuestionForm(QuestionForm form) {
        // Intentionally left blank
    }

    @Override
    public void generateVisibleIds(List<String> visibleIds) {
        // Intentionally left blank
    }
}
