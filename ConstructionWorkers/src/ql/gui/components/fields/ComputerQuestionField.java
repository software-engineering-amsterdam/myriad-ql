package ql.gui.components.fields;

import ql.astnodes.statements.ComputedQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.Widget;
import ql.gui.formenvironment.values.UndefinedValue;
import ql.gui.formenvironment.values.Value;

/**
 * Created by LGGX on 24-Feb-17.
 */
public class ComputerQuestionField extends Field {

    private Value value;

    public ComputerQuestionField(
            GUIInterface updates,
            ComputedQuestion question,
            Widget widget,
            Value value)
    {
        super(updates, question, widget);
        this.value = value;
    }

    @Override
    public void setState(Value value) {
        this.value = value;
    }

    @Override
    public Value getState() {
        return this.value;
    }

    @Override
    public void resetState() {
        this.value = new UndefinedValue();
    }

    public void setComputedValue(Value value) {
        this.widget.setValue(value);
    }
}
