package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class QlsStyle {
    private final List<Page> mPages;

    public QlsStyle(List<Page> pages) {
        mPages = pages;
    }

    public void applyStyle(Type type, Widget widget) {
        for (Page page : mPages) {
            if (page.contains(widget.getId())) {
                page.applyStyle(type, widget);
            }
        }
    }

    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        checkMissingIdentifiers(symbolTable, semanticsMessages);
        for (Page page : mPages) {
            page.checkSemantics(symbolTable, semanticsMessages);
        }
    }

    private void checkMissingIdentifiers(SymbolTable symbolTable, MessageList semanticsMessages) {
        Set<String> declaredQuestions = symbolTable.getUsedVariables();
        for (Page page : mPages) {
            for (String pageQuestion : page.getUsedVariables()) {
                if (!declaredQuestions.remove(pageQuestion)) {
                    semanticsMessages.addError("page " + page.getName() + ", question assigned multiple times: " + pageQuestion);
                }
            }
        }
        for (String questionName : declaredQuestions) {
            semanticsMessages.addError("Unassigned question: " + questionName);
        }
    }

    protected List<String> getPageNames() {
        List<String> pageNames = new ArrayList<>();
        for (Page page : mPages) {
            pageNames.add(page.getName());
        }
        return pageNames;
    }

    protected List<String> getSectionNames(String pageName) {
        for (Page page : mPages) {
            if (pageName.equals(page.getName())) {
                return page.getSectionNames();
            }
        }
        throw new RuntimeException("Unknown page queried: " + pageName);
    }

    public String getSectionName(String variableId) {
        for (Page page : mPages) {
            if (page.contains(variableId)) {
                return page.getSectionName(variableId);
            }
        }
        throw new RuntimeException("Unknown variable id queried: " + variableId);
    }
}
