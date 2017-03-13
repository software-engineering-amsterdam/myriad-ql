package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.operators.Operator;
import org.uva.taxfree.model.types.Type;

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
        return "(" + mLeft.resolveValue() + mOperator + mRight.resolveValue() + ")";
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
        if (!mLeft.isSameType(mRight)){
            semanticsMessages.addError("Incompatible types in expression" + mLeft.getType() + " & " + mRight.getType());
        }
        if (!mOperator.supports(mLeft.getType(), mRight.getType())) {
            semanticsMessages.addError("Unsupported operator called:");
        }
    }

    @Override
    public void getDependencies(Set<String> dependencies) {
        mLeft.getDependencies(dependencies);
        mRight.getDependencies(dependencies);
    }

    @Override
    public boolean isValid() {
        return mLeft.isSameType(mRight);
    }

    @Override
    public Type getType() {
        if (!isValid()) {
            throw new AssertionError("Either side works since the expression isn't valid anyway.");
        }
        return mLeft.getType();
    }
}
