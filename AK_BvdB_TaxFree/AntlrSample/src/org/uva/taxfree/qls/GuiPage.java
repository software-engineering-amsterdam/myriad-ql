package org.uva.taxfree.qls;

import org.uva.taxfree.ql.gui.widgets.Widget;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GuiPage {
    private final JPanel mPage;
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
        page.setName(name);
        page.add(new JLabel("Page: " + name));
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
        section.setName(sectionName);
        section.add(new JLabel("Section: " + sectionName));
        return section;
    }

    public void register(String sectionName, Widget widget) {
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
}
