package org.uva.taxfree.ql.model.node.literal;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.expression.ExpressionNode;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.values.Value;

import java.util.List;
import java.util.Set;

public abstract class LiteralNode extends ExpressionNode {

    protected LiteralNode(SourceInfo sourceInfo) {
        super(sourceInfo);
    }

    @Override
    public abstract Value evaluate();

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        // Intentionally left blank
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public void collectUsedVariables(Set<String> dependencies) {
        // Intentionally left blank
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        // intentionally left blank
    }

    @Override
    public void generateVisibleIds(List<String> visibleIds) {
        // intentionally left blank
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public boolean isLiteral() {
        return true;
    }
}
