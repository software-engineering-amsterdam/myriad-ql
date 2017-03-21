package org.uva.taxfree.ql.model.node.blocks;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.Node;
import org.uva.taxfree.ql.model.node.expression.ExpressionNode;
import org.uva.taxfree.ql.model.types.BooleanType;

import java.util.List;

public class IfStatementNode extends BlockNode {
    private final ExpressionNode mExpression;

    public IfStatementNode(ExpressionNode expression, List<Node> children, SourceInfo sourceInfo) {
        super(children, sourceInfo);
        mExpression = expression;
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        super.checkSemantics(symbolTable, semanticsMessages);
        mExpression.checkSemantics(symbolTable, semanticsMessages);
        if (!mExpression.getType().equals(new BooleanType())) {
            semanticsMessages.addError("Condition must be of boolean type!");
        }
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        super.fillSymbolTable(symbolTable);
        mExpression.fillSymbolTable(symbolTable);
    }

    @Override
    public boolean conditionTrue() {
        return mExpression.evaluate().equals("true");
    }
}
