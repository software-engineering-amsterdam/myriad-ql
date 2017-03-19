package org.qls.ast.page;

import org.qls.ast.widget.defaultWidget.DefaultWidget;

public interface SectionItemVisitor<T, C> {
    T visitSection(Section section, C context);
    T visitQuestion(Question question, C context);
    T visitDefaultWidget(DefaultWidget defaultWidget, C context);
}
