package org.ql.gui.elements;

import org.ql.evaluator.value.Value;
import org.ql.gui.QuestionPane;

public abstract class Element {

    public abstract boolean isDirty();

    public abstract void updateValue(Value value);

    public abstract void attachToPane(QuestionPane pane);
}
