package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.widgets.GuiComponent;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.Type;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Page {

    private final String mName;
    private final List<Section> mSections;
    private final List<DefaultStyle> mDefaultStyleDeclarations;
    private final SourceInfo mSourceInfo;

    public Page(String name, List<Section> sections, List<DefaultStyle> defaultStyles, SourceInfo sourceInfo) {
        mName = name;
        mSections = sections;
        mDefaultStyleDeclarations = defaultStyles;
        mSourceInfo = sourceInfo;

    }

    public String getName() {
        return mName;
    }

    public String getSectionName(String variableId) {
        for (Section section : mSections) {
            if (section.contains(variableId)) {
                return section.getName();
            }
        }
        throw new RuntimeException("Unknown variable id queried: " + variableId);
    }

    public List<String> getSectionNames() {
        List<String> sections = new ArrayList<>();
        for (Section section : mSections) {
            sections.add(section.getName());
        }
        return sections;
    }

    public Set<String> getUsedVariables() {
        Set<String> usedVariables = new HashSet<>();
        for (Section section : mSections) {
            usedVariables.addAll(section.getUsedVariables());
        }
        return usedVariables;
    }

    void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        checkDefaultStyles(semanticsMessages);
        checkUndeclaredIdentifiers(symbolTable, semanticsMessages);
        checkMissingIdentifiers(symbolTable, semanticsMessages);
    }

    private void checkDefaultStyles(MessageList semanticsMessages) {
        List<Type> processedTypes = new ArrayList<>();
        for (DefaultStyle declaration : mDefaultStyleDeclarations) {
            if (declaration.isOneOf(processedTypes)) {
                semanticsMessages.addError(declaration.sourceInfo() + "Duplicate type declaration: " + declaration.toString());
            }
        }
    }

    private void checkUndeclaredIdentifiers(SymbolTable symbolTable, MessageList semanticsMessages) {
        for (Section section : mSections) {
            section.checkSemantics(symbolTable, semanticsMessages);
        }
    }

    private void checkMissingIdentifiers(SymbolTable symbolTable, MessageList semanticsMessages) {
        Set<String> missingVariables = symbolTable.getUsedVariables();
        missingVariables.removeAll(getUsedVariables());
        for (String variable : missingVariables) {
            semanticsMessages.addError(mSourceInfo.sourceString() + "Question is not assigned to section: " + variable);
        }
    }

    public void applyStyle(Type type, GuiComponent component) {
        for (Section section : mSections) {
            if (section.contains(component.getId())) {
                section.applyStyle(component);
                return;
            }
        }

        for (StyleDeclaration declaration : mDefaultStyleDeclarations) {
            if (declaration.equals(type)) {
                declaration.applyStyle(component);
            }
        }
    }

    public boolean contains(String variableId) {
        for (Section section : mSections) {
            if (section.contains(variableId)) {
                return true;
            }
        }
        return false;
    }

}
