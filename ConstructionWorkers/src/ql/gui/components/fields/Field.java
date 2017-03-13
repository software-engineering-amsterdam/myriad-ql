/**
 * Field.java.
 */

package ql.gui.components.fields;

import ql.astnodes.statements.SimpleQuestion;
import ql.gui.GUIInterface;
import ql.gui.components.widgets.QLWidget;
import ql.gui.formenvironment.values.Value;

public abstract class Field  {

    private final GUIInterface guiInterface;

    protected final SimpleQuestion question;

    protected QLWidget widget;

    Field(GUIInterface guiInterface, SimpleQuestion question, QLWidget widget) {
        this.guiInterface = guiInterface;
        this.question = question;
        this.widget = widget;
    }

    protected void getNewChanges() {
        guiInterface.getGUIChanges(this);
    }

    public QLWidget getWidget() {
        return widget;
    }

    public String getId() {
        return question.getIdentifier().getName();
    }

    public abstract Value getValue();

    public abstract void setValue(Value value);

    public abstract void resetValue();
}
