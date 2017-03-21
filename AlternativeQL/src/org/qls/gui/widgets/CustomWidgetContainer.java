package org.qls.gui.widgets;

import org.ql.ast.identifier.Identifier;
import org.ql.gui.ValueReviser;
import org.ql.gui.widgets.*;
import org.ql.ast.statement.Question;
import org.ql.gui.widgets.Widget;
import org.qls.ast.widget.*;
import org.qls.ast.widget.default_widget.DefaultWidget;

import java.util.HashMap;
import java.util.Map;

public class CustomWidgetContainer implements WidgetVisitor<Widget, Question> {
    private final ValueReviser valueReviser;
    private final SpinboxFactory spinboxFactory;
    private final Map<Identifier, Widget> widgets;

    public CustomWidgetContainer(ValueReviser valueReviser) {
        this.valueReviser = valueReviser;
        spinboxFactory = new SpinboxFactory(valueReviser);
        widgets = new HashMap<>();
    }

    public Widget retrieveWidget(Question question, DefaultWidget defaultWidget) {
        Identifier questionId = question.getId();
        if (!widgets.containsKey(questionId)) {
            widgets.put(questionId, defaultWidget.getWidget().accept(this, question));
        }
        return widgets.get(questionId);
    }

    @Override
    public Widget visitCheckbox(Checkbox widget, Question question) {
        return new CheckboxWidget(valueReviser, question);
    }

    @Override
    public Widget visitDropdown(Dropdown widget, Question question) {
        return null;
    }

    @Override
    public Widget visitRadio(Radio widget, Question question) {
        return null;
    }

    @Override
    public Widget visitSlider(Slider widget, Question question) {
        return null;
    }

    @Override
    public Widget visitSpinbox(Spinbox widget, Question question) {
        return question.getType().accept(spinboxFactory, question);
    }

    @Override
    public Widget visitText(Text widget, Question question) {
        return new TextInputWidget(valueReviser, question);
    }
}
