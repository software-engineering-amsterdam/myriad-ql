package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.qls.styleoption.StyleOption;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QlsStyle {
    private final Map<String, List<StyleOption>> mQuestionStyles;
    private final List<StyleDeclaration> mStyleDeclarations;
    private final Map<String, List<String>> mSections;

    public QlsStyle() {
        mQuestionStyles = new HashMap<>();
        mSections = new HashMap<>();
        mStyleDeclarations = new ArrayList<>();
    }


    public void addVariableStyleDeclaration(Type variableType, List<StyleOption> styleOptions) {
        mStyleDeclarations.add(new StyleDeclaration(variableType, styleOptions));
    }

    public void applyStyle(Type type, JComponent component) {
        for (StyleDeclaration declaration : mStyleDeclarations) {
            if (declaration.equals(type)) {
                declaration.applyStyle(component);
            }
        }
    }

    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        checkDuplicateStyles(semanticsMessages);
    }

    private void checkDuplicateStyles(MessageList semanticsMessages) {
        List<Type> processedTypes = new ArrayList<>();
        for (StyleDeclaration declaration : mStyleDeclarations) {
            if (declaration.isOneOf(processedTypes)) {
                semanticsMessages.addError(declaration.sourceInfo() + "Duplicate declaration");
            }
        }
    }

}
