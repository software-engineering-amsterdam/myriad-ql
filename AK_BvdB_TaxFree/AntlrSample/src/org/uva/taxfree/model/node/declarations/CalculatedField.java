package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.types.Type;

import javax.swing.*;
import java.awt.*;

public class CalculatedField extends NamedNode {
    private final ExpressionNode mExpression;
    private final JTextField mTextField;
    private final Type mType;

    public CalculatedField(String label, String id, Type type, ExpressionNode expression) {
        super(label, id);
        mTextField = new JTextField();
        mTextField.setEditable(false);
        mTextField.setPreferredSize(new Dimension(100, 25));
        mType = type;
        mExpression = expression;
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        symbolTable.addDeclaration(this);
        symbolTable.addCalculation(this);
        symbolTable.addExpression(mExpression);
    }

    @Override
    protected void fillPanel(JPanel parent) {
        parent.add(mTextField);
    }

    public String toString() {
        return mExpression.toString();
    }

    @Override
    public void setVisible(boolean isVisible) {
        mTextField.setText(resolveValue());
        super.setVisible(isVisible);
    }

    @Override
    public String resolveValue() {
        return mExpression.evaluate();
    }

    @Override
    public Type getType() {
        return mType;
    }

}
