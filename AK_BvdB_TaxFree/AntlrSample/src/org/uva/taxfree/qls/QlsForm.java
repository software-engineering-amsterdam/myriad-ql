package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.environment.SymbolTable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QlsForm extends QuestionForm {
    private final QlsStyle mStyle;
    private final List<GuiPage> mPages;

    public QlsForm(String caption, SymbolTable symbolTable, QlsStyle qlsStyle) {
        super(caption, symbolTable);
        mStyle = qlsStyle;
        mPages = new ArrayList<>();
        createPages();
    }

    private void createPages() {
        for (String pageName : mStyle.getPageNames()) {
            mPages.add(new GuiPage(pageName, mStyle.getSectionNames(pageName)));
        }
    }

    @Override
    protected void registerToPanel(JPanel widgetPanel) {
        JTabbedPane tabbedPane = new JTabbedPane();
        for (GuiPage page : mPages) {
            JPanel panel = new JPanel();
            page.registerToPanel(panel);
            tabbedPane.add(page.getPageName(), panel);
        }
        widgetPanel.add(tabbedPane);
    }

    @Override
    protected void registerWidget(Widget widget) {
        String sectionName = mStyle.getSectionName(widget.getId());
        for (GuiPage page : mPages) {
            if (page.contains(sectionName)) {
                page.register(sectionName, widget);
            }
        }
        widget.updateStyle(mStyle);
    }


}
