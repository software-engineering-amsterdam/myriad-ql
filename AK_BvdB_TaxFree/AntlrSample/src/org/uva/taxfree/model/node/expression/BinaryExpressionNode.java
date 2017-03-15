package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.operators.Operator;
import org.uva.taxfree.model.types.Type;

import java.util.List;
import java.util.Set;

public class BinaryExpressionNode extends ExpressionNode {
    private final ExpressionNode mLeft;
    private final Operator mOperator;
    private final ExpressionNode mRight;

    public BinaryExpressionNode(ExpressionNode right, Operator operator, ExpressionNode left) {
        mRight = right;
        mOperator = operator;
        mLeft = left;
    }

    @Override
    public String resolveValue() {
        String leftHand = mLeft.resolveValue();
        String rightHand = mRight.resolveValue();
        String operator = mOperator.resolveValue();
        if (!leftHand.isEmpty() && !rightHand.isEmpty()) {
            return "(" + leftHand + operator + rightHand + ")";
        }
        return "";
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        mLeft.fillSymbolTable(symbolTable);
        mRight.fillSymbolTable(symbolTable);
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        mLeft.checkSemantics(symbolTable, semanticsMessages);
        mRight.checkSemantics(symbolTable, semanticsMessages);
        if (!mLeft.isSameType(mRight)) {
            semanticsMessages.addError("Incompatible types in expression: " + mLeft.getType() + " & " + mRight.getType());
        }
        if (!mOperator.supports(mLeft.getType(), mRight.getType())) {
            semanticsMessages.addError("Unsupported operator called:" + mLeft.getType() + " " + mOperator + " " + mRight.getType());
        }
    }

    @Override
    public void getDependencies(Set<String> dependencies) {
        mLeft.getDependencies(dependencies);
        mRight.getDependencies(dependencies);
    }

    @Override
    public void generateVisibleIds(List<String> visibleIds) {
        // intentionally left blank
    }

    @Override
    public Type getType() {
        return mOperator.getType();
    }
}
