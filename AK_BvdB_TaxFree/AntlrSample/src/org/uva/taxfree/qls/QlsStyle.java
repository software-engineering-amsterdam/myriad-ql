package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.qls.styleoption.StyleOption;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QlsStyle {
    private final List<Section> mSections;
    private final List<DefaultStyle> mDefaultStyleDeclarations;

    public QlsStyle() {
        mSections = new ArrayList<>();
        mDefaultStyleDeclarations = new ArrayList<>();
    }


    public void addVariableStyleDeclaration(Type variableType, List<StyleOption> styleOptions, SourceInfo sourceInfo) {
        mDefaultStyleDeclarations.add(new DefaultStyle(variableType, styleOptions, sourceInfo));
    }

    public void applyStyle(Type type, JComponent component) {
        for (StyleDeclaration declaration : mDefaultStyleDeclarations) {
            if (declaration.equals(type)) {
                declaration.applyStyle(component);
            }
        }
    }

    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        checkDefaultStyles(semanticsMessages);
        checkUndeclaredIdentifiers(symbolTable, semanticsMessages);
    }

    private void checkDefaultStyles(MessageList semanticsMessages) {
        List<Type> processedTypes = new ArrayList<>();
        for (DefaultStyle declaration : mDefaultStyleDeclarations) {
            if (declaration.isOneOf(processedTypes)) {
                semanticsMessages.addError(declaration.sourceInfo() + "Duplicate type declaration");
            }
        }
    }

    private void checkUndeclaredIdentifiers(SymbolTable symbolTable, MessageList semanticsMessages) {
        for (Section section : mSections) {
            section.checkSemantics(symbolTable, semanticsMessages);
        }
    }
}
