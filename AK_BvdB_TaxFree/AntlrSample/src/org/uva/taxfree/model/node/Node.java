package org.uva.taxfree.model.node;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.environment.SymbolTable;

import java.util.List;

public abstract class Node {
    public abstract void fillSymbolTable(SymbolTable symbolTable);

    public abstract void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages);

    public abstract void fillQuestionForm(QuestionForm form);

    public abstract void generateVisibleIds(List<String> visibleIds);
}