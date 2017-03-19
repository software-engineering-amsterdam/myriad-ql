package org.qls.ast.page;

import org.qls.ast.widget.defaultWidget.DefaultWidget;

import java.util.ArrayList;
import java.util.List;

public class NestedSectionVisitor implements SectionItemVisitor<Void, Void> {

    private List<Section> sections;

    public NestedSectionVisitor() {
        this.sections = new ArrayList<>();
    }

    public List<Section> getQuestionsFromSection(Section section) {
        sections.add(section);

        visitSection(section, null);

        return sections;
    }

    @Override
    public Void visitSection(Section section, Void context) {
        section.accept(this, null);

        return null;
    }

    @Override
    public Void visitQuestion(Question question, Void context) {
        return null;
    }

    @Override
    public Void visitDefaultWidget(DefaultWidget defaultWidget, Void context) {
        return null;
    }
}
