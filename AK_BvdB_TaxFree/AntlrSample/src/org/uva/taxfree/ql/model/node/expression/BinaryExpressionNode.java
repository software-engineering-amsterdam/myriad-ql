package org.uva.taxfree.ql.model.node.expression;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.operators.Operator;
import org.uva.taxfree.ql.model.types.Type;

import java.util.List;
import java.util.Set;

public class BinaryExpressionNode extends ExpressionNode {
    private final ExpressionNode mLeft;
    private final Operator mOperator;
    private final ExpressionNode mRight;

    public BinaryExpressionNode(ExpressionNode left, Operator operator, ExpressionNode right, SourceInfo sourceInfo) {
        super(sourceInfo);
        mLeft = left;
        mOperator = operator;
        mRight = right;
    }

    @Override
    public String asString() {
        return evaluate();
    }

    @Override
    public String evaluate() {
        return mOperator.evaluate(mLeft, mRight);
    }

    @Override
    public boolean asBoolean() {
        return Boolean.valueOf(evaluate());
    }

    @Override
    public int asInteger() {
        return Integer.valueOf(evaluate());
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        mLeft.fillSymbolTable(symbolTable);
        mRight.fillSymbolTable(symbolTable);
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        super.checkSemantics(symbolTable, semanticsMessages);
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
    public boolean isConstant() {
        return mLeft.isConstant() && mRight.isConstant();
    }

    @Override
    public boolean isLiteral() {
        return false;
    }

    @Override
    public void collectUsedVariables(Set<String> dependencies) {
        mLeft.collectUsedVariables(dependencies);
        mRight.collectUsedVariables(dependencies);
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
