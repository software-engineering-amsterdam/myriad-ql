package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;

import java.util.ArrayList;
import java.util.List;

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
                semanticsMessages.addWarning(mSourceInfo.sourceString() + ", section: " + mName + ", Undeclared identifier: " + question.sourceInfo() + " " + question.getId());
            } else {
                question.checkSemantics(symbolTable, semanticsMessages);
            }
        }
    }

    protected List<String> getUsedVariables() {
        List<String> usedVariables = new ArrayList<>();
        for (QuestionStyle questionStyle : mQuestionStyles) {
            usedVariables.add(questionStyle.getId());
        }
        return usedVariables;
    }

    protected String getName() {
        return mName;
    }

    protected boolean contains(String variableId) {
        return getUsedVariables().contains(variableId);
    }

    protected void applyStyle(Widget widget) {
        for (QuestionStyle style : mQuestionStyles) {
            if (style.getId().equals(widget.getId())) {
                style.applyStyle(widget);
            }
        }
    }
}
