package org.qls.gui.widgets;

import org.ql.ast.identifier.Identifier;
import org.ql.gui.ValueReviser;
import org.ql.gui.widgets.*;
import org.ql.ast.statement.Question;
import org.ql.gui.widgets.GUIWidget;
import org.ql.gui.widgets.container.SpinboxFactory;
import org.qls.ast.widget.*;

import java.util.HashMap;
import java.util.Map;

public class CustomWidgetContainer implements WidgetVisitor<GUIWidget, Question> {
    private final ValueReviser valueReviser;
    private final SpinboxFactory spinboxFactory;
    private final Map<Identifier, GUIWidget> widgets;

    public CustomWidgetContainer(ValueReviser valueReviser) {
        this.valueReviser = valueReviser;
        spinboxFactory = new SpinboxFactory(valueReviser);
        widgets = new HashMap<>();
    }

    public GUIWidget retrieveWidget(Question question, Widget widget) {
        Identifier questionId = question.getId();
        if (!widgets.containsKey(questionId)) {
            widgets.put(questionId, widget.accept(this, question));
        }
        return widgets.get(questionId);
    }

    @Override
    public GUIWidget visitCheckbox(Checkbox widget, Question question) {
        return new CheckboxWidget(valueReviser, question);
    }

    @Override
    public GUIWidget visitDropdown(Dropdown widget, Question question) {
        return new DropdownWidget(valueReviser, question, widget);
    }

    @Override
    public GUIWidget visitRadio(Radio widget, Question question) {
        return new RadioButtonWidget(valueReviser, question, widget);
    }

    @Override
    public GUIWidget visitSlider(Slider widget, Question question) {
        return new SliderWidget(valueReviser, question);
    }

    @Override
    public GUIWidget visitSpinbox(Spinbox widget, Question question) {
        return question.getType().accept(spinboxFactory, question);
    }

    @Override
    public GUIWidget visitText(Text widget, Question question) {
        return new TextInputWidget(valueReviser, question);
    }
}
