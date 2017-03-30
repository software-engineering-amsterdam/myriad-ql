package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.Type;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QlsStyle {
    private final List<Page> mPages;

    public QlsStyle(List<Page> pages) {
        mPages = pages;
    }

    public void applyStyle(Type type, JComponent component) {
        for (Page page : mPages) {
            if (page.contains(component.getName())) {
                page.applyStyle(type, component);
            }
        }
    }

    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        for (Page page : mPages) {
            page.checkSemantics(symbolTable, semanticsMessages);
        }
    }


    public List<String> getPageNames() {
        List<String> pageNames = new ArrayList<>();
        for (Page page : mPages) {
            pageNames.add(page.getName());
        }
        return pageNames;
    }

    public List<String> getSectionNames(String pageName) {
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
