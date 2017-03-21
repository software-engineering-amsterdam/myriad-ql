package org.uva.taxfree.ql.model.node.expression;

import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.node.Node;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.values.Value;

import java.util.List;
import java.util.Set;

public abstract class ExpressionNode extends Node {

    // Private because this class is abstract and may not be initialized
    protected ExpressionNode(SourceInfo sourceInfo) {
        super(sourceInfo);
    }

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

    public abstract Value evaluate();

    public abstract boolean isConstant();
}
