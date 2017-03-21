package org.uva.taxfree.ql.model.node.expression;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.Node;
import org.uva.taxfree.ql.model.types.Type;

import java.util.List;

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

    @Override
    public void fillQuestionForm(QuestionForm form) {
        // Intentionally left blank
    }

    @Override
    public void generateVisibleIds(List<String> visibleIds) {
        // Intentionally left blank
    }

    public abstract String evaluate();

    public abstract boolean asBoolean();

    public abstract int asInteger();

    public abstract String asString();

    @Override
    public abstract void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages);

    public abstract boolean isConstant();

    public abstract boolean isLiteral();
}
