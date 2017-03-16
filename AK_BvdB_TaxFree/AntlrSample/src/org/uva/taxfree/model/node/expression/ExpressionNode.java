package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.types.Type;

import java.util.List;
import java.util.Set;

public abstract class ExpressionNode extends Node {

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

    public abstract String evaluate();

    protected abstract boolean asBoolean();

    protected abstract int asInteger();

    protected abstract String asString();
}
