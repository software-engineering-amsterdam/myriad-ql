package org.ql.gui.widgets;

import org.ql.ast.statement.Question;

public class WidgetBuilder {

    public Widget getWidget(Question question) {

        // TODO: Use a visitor
        // TODO: Add date

        if(question.getType().isBoolean()) {
            return getBooleanQuestionWidget(question.getQuestionLabel().toString());
        }

        if(question.getType().isNumeric()) {
            return getNumericQuestionWidget(question.getQuestionLabel().toString());
        }

        if(question.getType().isString()) {
            return getStringQuestionWidget(question.getQuestionLabel().toString());
        }

        System.out.println("Woops, nothing returned!");

        return null;
    }

    public Widget getBooleanQuestionWidget(String label) {
        return new CheckBoxWidget(label);
    }

    public Widget getStringQuestionWidget(String label) {
        return new TextWidget(label);
    }

    public Widget getNumericQuestionWidget(String label) {
        return new NumericWidget(label);
    }
}
