package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.widgets.CalculationWidget;
import org.uva.taxfree.model.node.widgets.Widget;
import org.uva.taxfree.model.types.Type;

import javax.swing.*;
import java.util.Set;

public class DeclarationNode extends Node {
    private final String mId;
    private final String mLabel;
    private final Type mType;

    public DeclarationNode(String label, String id, Type type) {
        super();
        mLabel = label;
        mId = id;
        mType = type;
    }

    public Type getType() {
        return mType;
    }

    public JComponent getWidget() {
        Widget a = new CalculationWidget("Test");
        a.create();
        if (1 == 1) {
            throw new RuntimeException("Not implemented yet");
        }
        return new JCheckBox();
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        symbolTable.addDeclaration(this);
    }

    public String getId() {
        return mId;
    }

    public String getLabel() {
        return mLabel;
    }

    protected void getDependencies(Set<String> dependencies) {
        dependencies.add(mId);
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        // intentionally left blank
    }
}
