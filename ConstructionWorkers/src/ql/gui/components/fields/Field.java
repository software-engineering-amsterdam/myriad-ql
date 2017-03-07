/**
 * Field.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.GUIAbstractComponent;
import ql.gui.components.FormFrame;
import ql.gui.components.widgets.WidgetInterface;
import ql.gui.formenvironment.values.Value;

public abstract class Field extends GUIAbstractComponent {

    protected WidgetInterface widget;

    protected final SimpleQuestion question;

    Field(GUIInterface updates, SimpleQuestion question, WidgetInterface widget) {
        super(updates);
        this.question = question;
        this.widget = widget;
    }

    public void addToForm(FormFrame form) {
        widget.render(form);
    }

    public void removeFromForm(FormFrame form) {
        widget.suppress(form);
        resetState();
    }

    public WidgetInterface getWidget() {
        return widget;
    }

    public abstract Value getState();

    public abstract void setState(Value value);

    public abstract void resetState();

    @Override
    public String getId() {
        return question.getIdentifier().getName();
    }
}
