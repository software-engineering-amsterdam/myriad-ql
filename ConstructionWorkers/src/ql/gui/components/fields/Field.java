/**
 * Field.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.FormFrame;
import ql.gui.components.widgets.WidgetInterface;
import ql.gui.formenvironment.values.Value;

public abstract class Field  {

    protected WidgetInterface widget;
    protected final SimpleQuestion question;
    private final GUIInterface guiInterface;

    Field(GUIInterface guiInterface, SimpleQuestion question, WidgetInterface widget) {
        this.guiInterface = guiInterface;
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

    protected void getNewChanges() {
        guiInterface.getGUIChanges(this);
    }

    public abstract Value getState();

    public WidgetInterface getWidget() {
        return widget;
    }

    public abstract void setState(Value value);

    public abstract void resetState();

    public String getId() {
        return question.getIdentifier().getName();
    }
}
