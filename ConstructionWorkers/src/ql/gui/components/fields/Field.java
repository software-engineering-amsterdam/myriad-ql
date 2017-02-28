package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.GUIAbstractComponent;
import ql.gui.components.FormFrame;
import ql.gui.components.widgets.Widget;
import ql.gui.formenvironment.values.Value;

/**
 * Created by LGGX on 23-Feb-17.
 */
public abstract class Field extends GUIAbstractComponent {

    protected Widget widget;
    protected final SimpleQuestion question;

    Field(GUIInterface updates, SimpleQuestion question, Widget widget) {
        super(updates);
        this.question = question;
        this.widget = widget;
    }

    public abstract void setState(Value value);
    public abstract Value getState();

    public abstract void resetState();

    public void addToForm(FormFrame form) {
        this.widget.render(form);
    }

    public void removeFromForm(FormFrame form) {
        this.widget.suppress(form);
        this.resetState();
    }

    @Override
    public String getId() {
        return question.getIdentifier().getName();
    }

    public Widget getWidget() {
        return this.widget;
    }
}
