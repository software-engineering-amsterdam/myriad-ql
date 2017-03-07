/**
 * WidgetFactory.java.
 */

package ql.gui.components.widgets;

import ql.astnodes.statements.SimpleQuestion;
import ql.astnodes.types.*;
import ql.astnodes.visitors.TypeVisitor;
import ql.gui.formenvironment.values.Value;

public class WidgetFactory implements TypeVisitor<WidgetInterface> {

    private String questionLabel;

    public WidgetInterface getWidgetForSimpleQuestion(SimpleQuestion question) {
        Type type = question.getType();
        questionLabel = question.getLabel();
        return type.accept(this);
    }

    public WidgetInterface getWidgetForComputedQuestion(SimpleQuestion question, Value value) {
        Type type = question.getType();
        questionLabel = question.getLabel();

        WidgetInterface widget = type.accept(this);
        widget.setValue(value);
        widget.setReadOnly(true);
        return widget;
    }

    public WidgetInterface visit(BooleanType type) {
        return new Checkbox(questionLabel);
    }

    public WidgetInterface visit(IntegerType type) {
        return new IntegerTextField(questionLabel);
    }

    public WidgetInterface visit(MoneyType type) {
        return new MoneyTextField(questionLabel);
    }

    public WidgetInterface visit(StringType type) {
        return new TextField(questionLabel);
    }
}
