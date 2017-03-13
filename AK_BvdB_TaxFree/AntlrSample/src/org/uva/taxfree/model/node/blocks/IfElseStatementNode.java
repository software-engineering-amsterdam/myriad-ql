package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.expression.ExpressionNode;

import java.util.List;

public class IfElseStatementNode extends IfStatementNode {
    private final List<Node> mElseChildren;

    public IfElseStatementNode(ExpressionNode expressionNode, List<Node> thenStatementNodes, List<Node> elseStatementNodes) {
        super(expressionNode, thenStatementNodes);
        mElseChildren = elseStatementNodes;
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        super.fillSymbolTable(symbolTable);
        for (Node n : mElseChildren) {
            n.fillSymbolTable(symbolTable);
        }
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        super.checkSemantics(symbolTable, semanticsMessages);
        for (Node n : mElseChildren) {
            n.checkSemantics(symbolTable, semanticsMessages);
        }
    }

    @Override
    public void fillQuestionForm(QuestionForm form) {
        super.fillQuestionForm(form);
        for (Node n : mElseChildren) {
            n.fillQuestionForm(form);
        }
    }
}