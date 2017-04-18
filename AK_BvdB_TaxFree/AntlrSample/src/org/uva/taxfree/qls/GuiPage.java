package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.widgets.Widget;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.List;

public class GuiPage {
    public final JPanel mPage;
    private final List<JPanel> mSections;

    public GuiPage(String pageName, List<String> sectionNames) {
        mPage = createPage(pageName);
        mSections = createSections(sectionNames);
        registerSections();
    }

    private void registerSections() {
        for (JPanel section : mSections) {
            mPage.add(section);
        }
    }

    private JPanel createPage(String name) {
        JPanel page = new JPanel();
        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));
        page.setName(name);
        return page;
    }

    private List<JPanel> createSections(List<String> sectionNames) {
        List<JPanel> sections = new ArrayList<>();
        for (String sectionName : sectionNames) {
            sections.add(createSection(sectionName));
        }
        return sections;
    }

    private JPanel createSection(String sectionName) {
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setName(sectionName);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(sectionName);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        section.setBorder(titledBorder);
        return section;
    }

    protected void register(String sectionName, Widget widget) {
        for (JPanel section : mSections) {
            if (sectionName.equals(section.getName())) {
                widget.registerToPanel(section);
            }
        }
    }

    public void registerToPanel(JPanel panel) {
        panel.add(mPage);
    }

    public boolean contains(String sectionName) {
        for (JPanel panel : mSections) {
            if (panel.getName().equals(sectionName)) {
                return true;
            }
        }
        return false;
    }

    public String getPageName() {
        return mPage.getName();
    }
}
