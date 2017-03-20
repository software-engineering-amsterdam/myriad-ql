package org.uva.taxfree.ql.model.node;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.model.environment.SymbolTable;

import java.util.List;

public abstract class Node {
    public abstract void fillSymbolTable(SymbolTable symbolTable);

    public abstract void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages);

    public abstract void fillQuestionForm(QuestionForm form);

    public abstract void generateVisibleIds(List<String> visibleIds);
}