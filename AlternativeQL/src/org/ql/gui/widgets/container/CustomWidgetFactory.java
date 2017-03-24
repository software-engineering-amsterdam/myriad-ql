package org.ql.gui.widgets.container;

import org.ql.ast.statement.Question;
import org.ql.gui.ValueReviser;
import org.ql.gui.widgets.*;
import org.qls.ast.widget.*;

public class CustomWidgetFactory implements WidgetVisitor<GUIWidget, Question> {
    private final ValueReviser valueReviser;
    private final SpinboxFactory spinboxFactory;

    public CustomWidgetFactory(ValueReviser valueReviser) {
        this.valueReviser = valueReviser;
        spinboxFactory = new SpinboxFactory(valueReviser);
    }

    public GUIWidget createWidget(Question question, Widget widget) {
        return widget.accept(this, question);
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
