/**
 * Field.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.QLWidget;
import ql.gui.formenvironment.values.Value;

public abstract class Field  {

    protected QLWidget widget;
    protected final SimpleQuestion question;
    private final GUIInterface guiInterface;

    Field(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
        this.guiInterface = guiInterface;
        this.question = question;
        this.widget = widget;
    }

    protected void getNewChanges() {
        guiInterface.getGUIChanges(this);
    }

    public abstract Value getState();

    public QLWidget getWidget() {
        return widget;
    }

    public abstract void setState(Value value);

    public abstract void resetState();

    public String getId() {
        return question.getIdentifier().getName();
    }
}
