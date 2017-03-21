package org.qls.ast.page;

public interface WidgetQuestionVisitor<T, C> {
    T visitCustomWidgetQuestion(CustomWidgetQuestion question, C context);
    T visitGenericWidgetQuestion(WidgetQuestion question, C context);
}
