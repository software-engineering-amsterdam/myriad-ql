package org.qls.ast.page;

import org.qls.ast.widget.defaultWidget.DefaultWidget;

import java.util.ArrayList;
import java.util.List;

public class SectionQuestionsVisitor implements SectionItemVisitor<Void, Void>  {

    private List<Question> questions;

    public SectionQuestionsVisitor() {
        this.questions = new ArrayList<>();
    }

    public List<Question> getQuestionsFromSection(Section section) {
        visitSection(section, null);

        return questions;
    }

    @Override
    public Void visitSection(Section section, Void context) {
        section.accept(this, null);

        return null;
    }

    @Override
    public Void visitQuestion(Question question, Void context) {
        questions.add(question);

        return null;
    }

    @Override
    public Void visitDefaultWidget(DefaultWidget defaultWidget, Void context) {
        return null;
    }
}
