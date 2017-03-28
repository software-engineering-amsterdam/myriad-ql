package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.environment.SymbolTable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Section {
    private final String mName;
    private final List<String> mQuestions;

    public Section(String name, List<String> questions) {
        mName = name;
        mQuestions = questions;
    }

    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        for (String question : mQuestions) {
            if (!symbolTable.contains(question)) {
                semanticsMessages.addWarning("Undeclared identifier, section: " + mName + ", question: " + question);
            }
        }
    }

    protected Set<String> getUsedVariables() {
        Set<String> usedVariables = new HashSet<>();
        usedVariables.addAll(mQuestions);
        return usedVariables;
    }
}
