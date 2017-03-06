package org.ql.gui.widgets;

import org.ql.ast.statement.Question;

public class WidgetBuilder {

    public Widget getWidget(Question question) {
        if(question.getType().isBoolean()) {
            return getBooleanQuestionWidget(question.getQuestionText().toString());
        }

        System.out.println("Woops, nothing returned!");
        return null;
    }

    public Widget getBooleanQuestionWidget(String label) {
        return new CheckBoxWidget(label);
    }
}
