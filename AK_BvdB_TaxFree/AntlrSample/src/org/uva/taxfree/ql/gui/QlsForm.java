package org.uva.taxfree.ql.gui;

import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.qls.GuiPage;
import org.uva.taxfree.qls.QlsStyle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QlsForm extends QuestionForm {
    private final QlsStyle mStyle;
    private List<GuiPage> mPages;

    public QlsForm(String caption, SymbolTable symbolTable, QlsStyle qlsStyle) {
        super(caption, symbolTable);
        mStyle = qlsStyle;
        mPages = new ArrayList();
        createPages();
    }

    private void createPages() {
        for (String pageName : mStyle.getPageNames()) {
            mPages.add(new GuiPage(pageName, mStyle.getSectionNames(pageName)));
        }
    }

    @Override
    protected void registerToPanel(JPanel widgetPanel) {
        for (GuiPage page : mPages) {
            page.registerToPanel(widgetPanel);
        }
    }

    @Override
    protected void registerWidget(Widget widget) {
        widget.updateStyle(mStyle);
        for (GuiPage page : mPages) {
            if (page.contains(widget.getId())) {
                String sectionName = mStyle.getSectionName(widget.getId());
                page.register(sectionName, widget);
            }
        }
    }


}
