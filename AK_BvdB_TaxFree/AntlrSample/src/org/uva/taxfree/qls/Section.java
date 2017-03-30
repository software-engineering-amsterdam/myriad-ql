package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Section {
    private final String mName;
    private final List<QuestionStyle> mQuestionStyles;
    private final SourceInfo mSourceInfo;

    public Section(String name, List<QuestionStyle> questionStyles, SourceInfo sourceInfo) {
        mName = name;
        mQuestionStyles = questionStyles;
        mSourceInfo = sourceInfo;
    }

    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        for (QuestionStyle question : mQuestionStyles) {
            if (!symbolTable.contains(question.getId())) {
                semanticsMessages.addWarning("Undeclared identifier, section: " + mName + ", question: " + question);
            }
        }
    }

    protected Set<String> getUsedVariables() {
        Set<String> usedVariables = new HashSet<>();
        for (QuestionStyle questionStyle : mQuestionStyles) {
            usedVariables.add(questionStyle.getId());
        }
        return usedVariables;
    }

    public String getName() {
        return mName;
    }

    protected boolean contains(String variableId) {
        return getUsedVariables().contains(variableId);
    }
}
